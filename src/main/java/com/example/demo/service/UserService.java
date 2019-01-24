package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

import javax.annotation.Resource;

@Service("userService")
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void addNewUser(User user){
        userMapper.insert(user);
    }

}
