package com.example.demo.service;

import com.example.demo.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.example.demo.model.Customer;

@Service("CustomerService")
public class CustomerService {
    @Resource
    CustomerMapper customerMapper;

    public int addCustomer(Customer customer){
        int result = customerMapper.insert(customer);
        return result;
    }
}
