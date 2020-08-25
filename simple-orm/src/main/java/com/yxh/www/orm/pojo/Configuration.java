package com.yxh.www.orm.pojo;



import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    /**
     * 数据库连接信息
     */
    private DataSource dataSource;
    /**
     * SQL映射配置存储
     * key:由xxxMapper里的namespace+.+具体SQL映射配置的id组成
     * value:SQL映射配置
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
