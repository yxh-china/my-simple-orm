package com.yxh.www.testorm;

import com.yxh.www.orm.session.SqlSession;
import com.yxh.www.orm.session.SqlSessionFactory;
import com.yxh.www.orm.session.SqlSessionFactoryBuilder;
import com.yxh.www.orm.util.Resource;
import com.yxh.www.testorm.entity.SmUser;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws PropertyVetoException, DocumentException, SQLException {
        InputStream resourceAsStream = Resource.getResourceAsStream("dbConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<SmUser> smUserList = sqlSession.selectList("smUser.listSmUser", SmUser.class);
        SmUser params = new SmUser();
        params.setId("1");
        sqlSession.selectOne("smUser.getSmUser",SmUser.class,params);

    }
}
