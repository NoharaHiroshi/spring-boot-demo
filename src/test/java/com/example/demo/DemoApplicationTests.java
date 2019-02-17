package com.example.demo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.UserController;

@RunWith(SpringRunner.class)
// classes 指定启动类，WebEnvironment.RANDOM_PORT经常和测试类中@LocalServerPort一起在注入属性时使用。会随机生成一个端口号。
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    // 在所有测试方法前执行一次，一般在其中写上整体初始化的代码
    @BeforeClass

    // 在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码
    @AfterClass

    //在每个测试方法前执行，一般用来初始化方法
    @Before

    @Test
    public void contextLoads() {
    }

}

