package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 所有的springboot application启动类都需要在类级别上加上@SpringBootApplication注解
// 可以选择把一些初始化的动作放到该类中进行
@SpringBootApplication
// org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userController': Injection of resource dependencies failed;
// 出现这个问题，可能是mapper层没有被扫描到
@MapperScan("com.example.demo")
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}

