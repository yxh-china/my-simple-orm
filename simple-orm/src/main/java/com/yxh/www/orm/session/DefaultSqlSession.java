package com.yxh.www.orm.session;

import com.yxh.www.orm.executor.Executor;
import com.yxh.www.orm.executor.SimpleExecutor;
import com.yxh.www.orm.pojo.Configuration;
import com.yxh.www.orm.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> E execute(String statementId) throws Exception {
        List<E> list = this.executeReturnList(statementId);
        if (list == null||list.isEmpty()){
            return null;
        }else if (list.size()>1){
            throw new RuntimeException("查询结果超过1条");
        }else {
            return list.get(0);
        }
    }

    public <E> E execute(String statementId, Object... params) throws Exception {
        List<E> list = this.executeReturnList(statementId, params);
        if (list == null||list.isEmpty()){
            return null;
        }else if (list.size()>1){
            throw new RuntimeException("查询结果超过1条");
        }else {
            return list.get(0);
        }
    }

    public <E> List<E> executeReturnList(String statementId) throws Exception {
        return this.executeReturnList(statementId,null);
    }

    public <E> List<E> executeReturnList(String statementId, Object... params) throws Exception {
        Executor executor = new SimpleExecutor();
        return executor.query(this.configuration, configuration.getMappedStatementMap().get(statementId), params);
    }


    public <M> M getMapper(Class<M> mClass) {
        M m=(M) Proxy.newProxyInstance(mClass.getClassLoader(), new Class[]{mClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 获取方法名
                String methodName = method.getName();
                String className = method.getDeclaringClass().getSimpleName();
                // statementId
                String statementId=className+"."+methodName;
                Type genericReturnType=method.getGenericReturnType();
                // 判断是否实现泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    return executeReturnList(statementId,args);
                }
                return execute(statementId,Object.class,args);
            }
        });
        return m;
    }

}
