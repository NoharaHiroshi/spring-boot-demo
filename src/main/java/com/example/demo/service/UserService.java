package com.example.demo.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

@Service("userService")
public class UserService {

    @Resource
    UserMapper userMapper;

    public String addNewUser(User user){
        int result = userMapper.insert(user);
        System.out.println(result);
        return "addNewUser Success";
    }

}
