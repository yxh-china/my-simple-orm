package com.yxh.www.orm.util;

import java.io.InputStream;

public class Resource {

    /**
     * 根据配置文件的路径，把配置文件加载成字节输入流，放到内存中
     * @param path  配置文件路径
     * @return  字节输入流
     */
    public static InputStream getResourceAsStream(String path){
        InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
