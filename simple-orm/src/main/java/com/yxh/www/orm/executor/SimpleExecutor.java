package com.yxh.www.orm.executor;

import com.yxh.www.orm.pojo.Configuration;
import com.yxh.www.orm.pojo.MappedStatement;
import com.yxh.www.orm.util.BountSQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SimpleExecutor implements Executor{
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException {
        // 获取数据库连接
        Connection connection = configuration.getDataSource().getConnection();
        // 取得SQL语句
        String sql = mappedStatement.getSql();
        // 把参数替换到SQL语句内
        BountSQL bountSQL = getBountSQL(sql);
        return null;
    }

    /**
     * 把参数放到替换到SQL内
     * @param sql   配置文件内原生SQL
     */
    private BountSQL getBountSQL(String sql) {
        return null;
    }
}
