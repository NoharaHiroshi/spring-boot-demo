package com.example.demo.service;

import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.example.demo.model.Customer;
import com.example.demo.model.User;

@Service("CustomerService")
public class CustomerService {
    @Resource
    CustomerMapper customerMapper;

    @Resource
    UserMapper userMapper;

    public int addCustomer(Customer customer){
        Integer userId = customer.getId();
        User user = userMapper.selectByPrimaryKey(userId);
        System.out.println(user);
        if(user != null){
            int result = customerMapper.insert(customer);
            return result;
        }else {
            return -1;
        }

    }

    public Customer getCustomerAndUser(Integer customerId) {
        Customer customer = customerMapper.getCustomerAndUser(customerId);
        return customer;
    }
}
