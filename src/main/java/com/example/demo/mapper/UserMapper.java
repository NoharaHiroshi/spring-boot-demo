package com.example.demo.mapper;

import com.example.demo.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKey(User record);
}