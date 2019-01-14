package com.example.demo.controller;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

import javax.annotation.Resource;

import javassist.bytecode.ByteArray;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import com.google.gson.Gson;

import com.example.demo.model.User;


// @RestController注解能够使项目支持Rest，即返回数据的格式为json
@RestController
@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value="/user")
public class userController {
    // 从前端传送过来的数据方式
    // GET方式:
    // 1、用@RequestParam方式接受参数
    // POST方式:
    // 1、用json方式接收数据，使用@RequestBody注解接收参数。接收到的参数，如果参数类型为String，
    // 则为字符串类型，如果参数类型为模型类型，则为模型对象
    // 2、用表单方式接受数据，使用@RequstParam接收数据

//    @RequestMapping(value = "/testJson", method = RequestMethod.GET)
//    public @ResponseBody User testJson(){
//        User user = new User();
//        user.setName("Lands");
//        user.setId(1);
//        return user;
//    }

    // 这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/helloUser", method = RequestMethod.GET)
    // @RequestParam接收value的值作为属性名去查找相应的值
    // 接收参数的方法：@RequestParam， 参数类型， 参数名称
    // 给出的参数必须传输，如果不传参数，则会报错。
    // 如果给出的参数不一定会使用，则需要加上default，或者required=false。否则会报错
    public String index(@RequestParam(value = "name", defaultValue = "World") String name) {
        User user = new User();
        user.setName(name);
        user.setId(1);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    // 使用POST方式请求接口，如果请求参数为json格式，获取参数需要用到注解@RequestBody，
    // 如果请求参数为form格式，获取参数需要用到@RequestParam
    public String addUser(@RequestBody String user){
        // 原来java可以直接映射json字符串为对象，难怪他说要我按照模型字段来传数据了
        // 看来java的接口思路就是通过对象实例传输，前端对象 <=> 后端对象 <=> 数据库对象 保持一致
//        System.out.println(user);
//        System.out.println(user.getId());
//        System.out.println(user.getName());
        return user;
    }

    // RequestParam接收的参数是json格式的，不指定参数名，会默认使用属性名作为参数名
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

    @RequestMapping(value = "/uploadFileAndInfo", method = RequestMethod.POST)
    public String uploadFileAndInfo(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "user") String user
    ){
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        System.out.println(user);
        String filePath = "F:" + File.separator + "translation_pdf" + File.separator + file.getOriginalFilename();
        File f = new File(filePath);
        if(!f.getParentFile().exists()){ //判断文件父目录是否存在
            f.getParentFile().mkdir();
        }
        try {
            file.transferTo(f);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

}