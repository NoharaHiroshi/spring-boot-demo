package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

import javax.annotation.Resource;

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
