package com.yxh.www.orm.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yxh.www.orm.pojo.Configuration;
import com.yxh.www.orm.util.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 配置文件装配器
 */
public class XMLConfigBuilder {
    private final Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 将配置文件字节输入流，解析封装成Configuration对象
     * @param xmlInputStream 配置文件字节输入流
     * @return  {@link Configuration} 核心配置文件容器对象
     */
    public Configuration parseConfig(InputStream xmlInputStream) throws DocumentException, PropertyVetoException, ClassNotFoundException {
        // 把字节输入流使用dom4j解析成Document对象
        Document document = new SAXReader().read(xmlInputStream);
        // 拿到配置文件的根元素对象
        Element rootElement = document.getRootElement();
        // 取出配置文件内<dataSource>所有的<property>标签元素
        Element dataSourceElement = rootElement.element("dataSource");
        List<Element> dataSourceElements = dataSourceElement.elements();
        Properties configProperties=new Properties();
        for (Element property : dataSourceElements) {
            configProperties.setProperty(property.attribute("name").getStringValue(),property.getText());
        }
        // 使用c3p0连接池创建数据源对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource("c3p0");;
        dataSource.setDriverClass(configProperties.getProperty("driverClass"));
        dataSource.setJdbcUrl(configProperties.getProperty("url"));
        dataSource.setUser(configProperties.getProperty("user"));
        dataSource.setPassword(configProperties.getProperty("pass"));
        this.configuration.setDataSource(dataSource);
        // 解析xxxMapper.xml
        Element mappersElement = rootElement.element("mappers");
        List<Element> mapperElementList = mappersElement.elements();
        // 取出每一个配置文件的Mapper文件的路径
        for (Element mapperElement : mapperElementList) {
            // 获取Mapper配置文件的字节输入流
            InputStream mapperInputStream = Resource.getResourceAsStream(mapperElement.attribute("xml").getStringValue());
            // 解析Mapper配置文件
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(this.configuration);
            xmlMapperBuilder.parse(mapperInputStream);
        }
        return this.configuration;
    }
}
