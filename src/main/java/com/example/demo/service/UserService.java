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
        if(user == null){
            throw new RuntimeException("检查用户参数是否为空");
        }
        int result = userMapper.insert(user);
        System.out.println(result);
        return "addNewUser Success";
    }

    public User[] getAllUser() {
        User[] userList = userMapper.selectAll();
        return userList;
    }

    public User[] searchUserByName(String name) {
        User[] userList = userMapper.getUserByName(name);
        return userList;
    }

}
