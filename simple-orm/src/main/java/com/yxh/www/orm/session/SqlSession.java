package com.yxh.www.orm.session;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {
     <E> List<E> selectList(String statementId,Class<E> eClass,Object... params) throws Exception;
     <E> E selectOne(String statementId,Class<E> eClass,Object... params) throws Exception;
     <M> M getMapper(Class<M> mClass);
}
