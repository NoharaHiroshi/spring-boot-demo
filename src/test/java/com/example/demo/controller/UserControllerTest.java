package com.example.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.User;

import static org.junit.Assert.*;

public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    // Mock可以模拟对http的请求，不依赖网络环境，并提供了一套验证工具
    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setupMockMvc() {
        // 初始化mvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setId(4);
        user.setName("LJC");
    }

    @Test
    public void getAllUser() {
    }
}