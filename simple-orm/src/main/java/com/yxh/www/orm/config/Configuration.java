package com.yxh.www.orm.config;

import com.yxh.www.orm.pojo.MappedStatement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 框架核心配置文件
 * 数据库连接配置和SQL映射配置都封装到这个类里
 * @author yangxiaohui
 */
public class Configuration {
    /**
     * 数据库连接相关配置
     */
    private DataSource dataSource;
    /**
     * SQL映射清单信息
     */
    private Map<String, MappedStatement> mappedStatementMap=new HashMap<String, MappedStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
