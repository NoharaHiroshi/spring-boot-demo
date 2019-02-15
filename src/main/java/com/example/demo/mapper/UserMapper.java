package com.example.demo.mapper;

import com.example.demo.model.User;

// 与python的sqlAlchemy相比，最大的不同是sqlAlchemy是全自动的ORM，它设计了一套规范，让用户可以针对实际需要实时的、灵活的
// 创建sql语句。
// SpringBoot使用的mybatis是事先将可能对对象操作（增删查改）建立好，然后按需使用。
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User[] selectAll();

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}