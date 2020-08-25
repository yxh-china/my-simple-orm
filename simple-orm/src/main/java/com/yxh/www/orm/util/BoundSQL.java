package com.yxh.www.orm.util;

import java.util.ArrayList;
import java.util.List;

public class BoundSQL {
    //解析过后的SQL语句
    private String sqlText;
    //解析出来的参数
    private List<ParameterMapping> parameterMappingList = new
            ArrayList<ParameterMapping>();
    public BoundSQL(String sqlText, List<ParameterMapping>
            parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }
    public String getSqlText() {
        return sqlText;
    }
    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }
    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }
    public void setParameterMappingList(List<ParameterMapping>
                                                parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
