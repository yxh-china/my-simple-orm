package com.yxh.www.testorm.dao;

import com.yxh.www.testorm.entity.SmUser;

import java.util.List;

public interface SmUserDao {
    List<SmUser> listSmUser(SmUser smUser);
}
