package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 所有的springboot application启动类都需要在类级别上加上@SpringBootApplication注解
// 可以选择把一些初始化的动作放到该类中进行
@SpringBootApplication
@MapperScan("com.example.demo")
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}

