package com.yxh.www.test;

import java.util.HashMap;
import java.util.Map;

/**
 * SQL映射配置清单对象
 * 每个SQL映射配置文件对应一个MappedStatement对象
 * @author yangxiaohui
 */
public class MappedStatement {
    /**
     * SQL映射配置文件命名空间，全局唯一标识
     */
    private String namespace;
    /**
     * 该SQL映射配置清单内的所有SQL映射配置信息，映射配置的id作为Map的key
     */
    private Map<String,Mapped> mappedMap=new HashMap<String, Mapped>();


    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Map<String, Mapped> getMappedMap() {
        return mappedMap;
    }

    public void setMappedMap(Map<String, Mapped> mappedMap) {
        this.mappedMap = mappedMap;
    }
}
