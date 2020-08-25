package com.yxh.www.orm.config;

/**
 * SQL映射配置信息，包括SQL映射ID、SQL参数类型、SQL结果集类型、SQL内容 配置信息
 * @author yangxiaohui
 */
public class Mapped {
    /**
     * SQL映射ID
     */
    private String id;
    /**
     * SQL参数类型
     */
    private Class<?> paramType;
    /**
     * SQL结果集类型
     */
    private Class<?> resultType;
    /**
     * SQL内容
     */
    private String sql;

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
}
