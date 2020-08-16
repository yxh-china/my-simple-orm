package com.yxh.www.orm.config;

import com.yxh.www.orm.pojo.Configuration;
import com.yxh.www.orm.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {
    private final Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration=configuration;
    }
    public void parse(InputStream mapperInputStream) throws DocumentException {
        // 转换Document
        Document mapperDocument = new SAXReader().read(mapperInputStream);
        // 获取根元素
        Element rootElement = mapperDocument.getRootElement();
        // 获取namespace
        String namespace = rootElement.attribute("namespace").getStringValue();
        // 获取每一个子级别元素（SQL映射配置元素)
        List<Element> mappedStatementElement = rootElement.elements();
        for (Element element : mappedStatementElement) {
            String id = element.attribute("id").getStringValue();
            String resultType = element.attribute("resultType").getStringValue();
            String paramType = element.attribute("paramType").getStringValue();
            String sql = element.getText();
            String sqlQueryType = element.getName();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamType(paramType);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sql);
            mappedStatement.setQueryType(sqlQueryType);
            this.configuration.getMappedStatementMap().put(namespace+"."+id,mappedStatement);
        }
    }
}
