package com.yxh.www.orm.session;

import com.yxh.www.orm.executor.Executor;
import com.yxh.www.orm.executor.SimpleExecutor;
import com.yxh.www.orm.pojo.Configuration;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String statementId, Class<E> eClass, Object... params) throws SQLException {
        Executor executor = new SimpleExecutor();
        return executor.query(this.configuration, configuration.getMappedStatementMap().get(statementId), params);
    }

    public <E> E selectOne(String statementId, Class<E> eClass, Object... params) throws SQLException {
        List<E> list = this.selectList(statementId, eClass, params);
        if (list == null||list.isEmpty()){
            return null;
        }else if (list.size()>1){
            throw new RuntimeException("查询结果超过1条");
        }else {
            return list.get(0);
        }
    }
}
