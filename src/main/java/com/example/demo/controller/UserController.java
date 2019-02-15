package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.UserService;
import com.example.demo.model.User;


// @RestController注解能够使项目支持Rest，即返回数据的格式为json
@RestController
@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
// @Controller
@RequestMapping(value="/user")
public class UserController {
    // 从前端传送过来的数据方式
    // GET方式:
    // 1、用@RequestParam方式接受参数
    // POST方式:
    // 1、用json方式接收数据，使用@RequestBody注解接收参数。接收到的参数，如果参数类型为String，
    // 则为字符串类型，如果参数类型为模型类型，则为模型对象
    // 2、用表单方式接受数据，使用@RequstParam接收数据

    @Resource
    UserService userService;

    @Resource
    CustomerService customerService;

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
    public String addUser(@RequestBody User user){
        // 原来java可以直接映射json字符串为对象，难怪他说要我按照模型字段来传数据了
        // 看来java的接口思路就是通过对象实例传输，前端对象 <=> 后端对象 <=> 数据库对象 保持一致
        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getName());
        try {
            userService.addNewUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "getAllUser", method = RequestMethod.GET)
    public String getAllUser() {
        User[] userList = userService.getAllUser();
        Gson gson = new Gson();
        return gson.toJson(userList);
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
            Boolean result = f.getParentFile().mkdir();
        }
        try {
            file.transferTo(f);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping(value = "/uploadBinaryFile", method = RequestMethod.POST)
    public String uploadBinaryFile(@RequestBody Byte[] file){
        System.out.println(file);
        return "success";
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public String getFile(HttpServletResponse response) {
        try {
            String filePath = "F:" + File.separator + "translation_pdf" + File.separator + "fin.pdf";
            System.out.println(filePath);
            File f = new File(filePath);
            // 获取文件长度
            Long fileLength = f.length();
            // 文件读入流
            FileInputStream fileInputStream = new FileInputStream(f);
            response.setHeader("Content-Disposition", "inline;filename=" + f.getName());
            response.setHeader("Content-Type", "application/pdf");
            // response 的输出流
            OutputStream os = response.getOutputStream();
            // 建立一个字节流
            byte[] bytes = new byte[fileLength.intValue()];
            // 按照bytes大小将字节流存入bytes
            System.out.println(fileInputStream.read(bytes));
            // 写入bytes内容
            os.write(bytes);
            os.flush();
            os.close();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getFile1", method = RequestMethod.GET)
    public String getFile1(HttpServletResponse response){
        try {
            String filePath = "F:" + File.separator + "translation_pdf" + File.separator + "fin.pdf";
            // 我发现java中的file类只是用来操作文件本身的，比如说获取文件大小和文件名，判断是否可读可写，并不能操作文件的内容。
            FileInputStream op = new FileInputStream(filePath);
            int fileLength = op.available();
            byte[] bytes = new byte[fileLength];
            op.read(bytes);
            OutputStream responseOp = response.getOutputStream();
            responseOp.write(bytes);
            responseOp.flush();
            responseOp.close();
        } catch (Exception e){
            return null;
        }
        return null;
    }

    @RequestMapping(value="addCustomer", method = RequestMethod.GET)
    public String addCustomer(
            @RequestParam(value="id") Integer id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "user_id") Integer userId
    ){
        if (id != null && name != null && userId != null) {
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(name);
            customer.setUserId(userId);
            customerService.addCustomer(customer);
            return "创建成功";
        }else {
            return "参数不正确";
        }
    }

}