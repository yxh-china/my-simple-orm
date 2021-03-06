package com.yxh.www.orm.pojo;

/**
 * 存储xxxMapper.xml内的信息，每个SQL语句对应一个MappedStatement对象
 */
public class MappedStatement {
    private String id;
    private Class<?> paramType;
    private Class<?> resultType;
    private String sql;
    private String queryType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getParamType() {
        return paramType;
    }

    public void setParamType(Class<?> paramType) {
        this.paramType = paramType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
