package com.yxh.www.testorm;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yxh.www.orm.session.SqlSession;
import com.yxh.www.orm.session.SqlSessionFactory;
import com.yxh.www.orm.session.SqlSessionFactoryBuilder;
import com.yxh.www.orm.util.Resource;
import com.yxh.www.testorm.dao.SmUserDao;
import com.yxh.www.testorm.entity.SmUser;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        // 参数
        SmUser param=new SmUser();
        param.setId("2");
        // 获取配置信息
        InputStream resourceAsStream = Resource.getResourceAsStream("dbConfig.xml");
        // 创建数据库连接会话对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 调用SQL方法
        /*List<SmUser> smUserList = sqlSession.selectList("smUser.listSmUser", SmUser.class,param);
        for (SmUser smUser : smUserList) {
            System.out.println(smUser.toString());
        }*/
        // 使用代理方式
        SmUserDao smUserDao=sqlSession.getMapper(SmUserDao.class);
        List<SmUser> smUserList = smUserDao.listSmUser(param);
        for (SmUser smUser : smUserList) {
            System.out.println(smUser.toString());
        }
    }
}
