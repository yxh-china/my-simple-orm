package com.yxh.www.orm.session;

import java.util.List;

public interface SqlSession {
     /**
      * 调用S执行QL 返回单条结果
      */
     <E> E execute(String statementId)throws Exception;
     <E> E execute(String statementId,Object... params)throws Exception;
     /**
      * 调用S执行QL 返回多条结果
      */
     <E> List<E> executeReturnList(String statementId)throws Exception;
     <E> List<E> executeReturnList(String statementId,Object... params)throws Exception;
     <M> M getMapper(Class<M> mClass);
}
