package com.yxh.www.orm.session;

import com.yxh.www.orm.config.XMLConfigBuilder;
import com.yxh.www.orm.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException, ClassNotFoundException {
        // 使用dom4j解析配置文件,封装配置信息到容器对象中
        Configuration configuration = new XMLConfigBuilder().parseConfig(inputStream);
        // 创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory=new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;

    }
}
