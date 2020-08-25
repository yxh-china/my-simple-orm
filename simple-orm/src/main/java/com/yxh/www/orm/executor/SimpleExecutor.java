package com.yxh.www.orm.executor;

import com.yxh.www.orm.pojo.Configuration;
import com.yxh.www.orm.pojo.MappedStatement;
import com.yxh.www.orm.util.BoundSQL;
import com.yxh.www.orm.util.GenericTokenParser;
import com.yxh.www.orm.util.ParameterMapping;
import com.yxh.www.orm.util.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor{

    private Connection connection = null;

    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 获取数据库连接
        connection = configuration.getDataSource().getConnection();
        // 取得原生SQL语句
        String sql = mappedStatement.getSql();
        // 处理SQL#{}参数
        BoundSQL boundSQL = getBoundSQL(sql);
        // 获取要执行的SQL语句
        String finalSql = boundSQL.getSqlText();
        // 获取入参类型
        Class<?> paramType = mappedStatement.getParamType();
        //获取预编译PreparedStatement对象，预处理SQL并放入参数
        PreparedStatement preparedStatement =connection.prepareStatement(finalSql);
        List<ParameterMapping> parameterMappingList =boundSQL.getParameterMappingList();
        // 遍历处理入参
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String name = parameterMapping.getContent();
            // 反射
            Field declaredField = paramType.getDeclaredField(name);
            declaredField.setAccessible(true);
            // 参数值
            Object o = declaredField.get(params[0]);
            // 给占位符赋值
            preparedStatement.setObject(i+1,o);
        }
        // 执行SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        Class<?> resultType = mappedStatement.getResultType();
        // 结果集封装
        ArrayList<E> results = new ArrayList<E>();
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            E o = (E) resultType.newInstance();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                // 属性名
                String columnName = metaData.getColumnName(i);
                // 属性值
                Object value = resultSet.getObject(columnName);
                // 创建读写描述器，为属性生成读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultType);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);
            }
            results.add(o);
        }
        // 如果涉及数据的修改变动，需要提交事务
        if (mappedStatement.getQueryType().equals("insert")||mappedStatement.getQueryType().equals("update")||mappedStatement.getQueryType().equals("delete")) {
            connection.commit();
        }
        // 返回结果
        return results;
    }

    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替，2.解析出#{}里面的值进行存储
     * @param sql   配置文件内原生SQL
     */
    private BoundSQL getBoundSQL(String sql) {
        // 标记处理类：用来配合通用标记解析器GenericTokenParser类完成对配置文件等的解析工作，其中TokenHandler主要完成处理
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        // GenericTokenParser:通用标记解析器，完成代码片中的占位符的解析，然后根据给定的标记处理器TokenHandler来进行表达式的处理
        // 三个参数：分别为openToken（开始标记）、closeToken（结束标记）、handler（标记处理器）
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);
        // 解析出来的SQL
        String parse = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSQL(parse, parameterMappings);
    }
}
