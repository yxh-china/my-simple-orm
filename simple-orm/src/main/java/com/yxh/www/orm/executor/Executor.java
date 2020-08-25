package com.yxh.www.orm.executor;

import com.yxh.www.orm.pojo.Configuration;
import com.yxh.www.orm.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    <E>List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
