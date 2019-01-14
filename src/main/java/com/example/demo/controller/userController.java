package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;


// @RestController注解能够使项目支持Rest，即返回数据的格式为json
@RestController
@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value="/user")
public class userController {

    // 这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/helloUser", method = RequestMethod.GET)
    // @RequestParam接收value的值作为属性名去查找相应的值
    // 接收参数的方法：@RequestParam， 参数类型， 参数名称
    public String index(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    // 使用POST方式请求接口，如果请求参数为json格式，获取参数需要用到注解@RequestBody，
    // 如果请求参数为form格式，获取参数需要用到@RequestParam
    public String addUser(@RequestBody String user){
        // 原来java可以直接映射json字符串为对象，难怪他说要我按照模型字段来传数据了
        // 看来java的接口思路就是通过对象实例传输
//        System.out.println(user);
//        System.out.println(user.getId());
//        System.out.println(user.getName());
        return user;
    }

    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public String addCustomer(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "name") String name
    ){
        System.out.println(id);
        System.out.println(name);
        return "json data is " + name;
    }


    @RequestMapping(value = "/getUserId")
    public String getUser(@RequestParam(value = "id") String params){
        // 如果不指定RequestParam，会默认使用参数名作为属性名
        System.out.println(params);
        return params;
    }
}