package com.yxh.www.orm.session;

import com.yxh.www.orm.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;

    private DefaultSqlSessionFactory(){}
    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration=configuration;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(this.configuration);
    }
}
