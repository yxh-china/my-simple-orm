package com.yxh.www.orm.session;

import com.yxh.www.orm.executor.Executor;
import com.yxh.www.orm.executor.SimpleExecutor;
import com.yxh.www.orm.pojo.Configuration;
import com.yxh.www.orm.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String statementId, Class<E> eClass, Object... params) throws Exception {
        Executor executor = new SimpleExecutor();
        return executor.query(this.configuration, configuration.getMappedStatementMap().get(statementId), params);
    }

    public <E> E selectOne(String statementId, Class<E> eClass, Object... params) throws Exception {
        List<E> list = this.selectList(statementId, eClass, params);
        if (list == null||list.isEmpty()){
            return null;
        }else if (list.size()>1){
            throw new RuntimeException("查询结果超过1条");
        }else {
            return list.get(0);
        }
    }

    public <M> M getMapper(Class<M> mClass) {
        M m=(M) Proxy.newProxyInstance(mClass.getClassLoader(), new Class[]{mClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 获取方法名
                String methodName = method.getName();
                String className = method.getDeclaringClass().getSimpleName();
                // statementId
                String statementId=className+"."+methodName;
                MappedStatement mappedStatement=configuration.getMappedStatementMap().get(statementId);
                Type genericReturnType=method.getGenericReturnType();
                ArrayList arrayList = new ArrayList ();
                // 判断是否实现泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    return selectList(statementId,List.class,args);
                }
                return selectOne(statementId,Object.class,args);
            }
        });
        return m;
    }

}
