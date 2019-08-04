package com.nchu16201533.post.service;

import com.nchu16201533.post.bean.Customer;
import com.nchu16201533.post.bean.CustomerExample;
import com.nchu16201533.post.dao.CustomerMapper;
import com.nchu16201533.post.util.EncryptBasedDes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 16201533
 * @Date: 2019/6/1 12:09
 * @Version 1.0
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public boolean register(Customer customer) {
        customer.setPassword(EncryptBasedDes.encryptBasedDes(customer.getPassword()));
        return customerMapper.insertSelective(customer) > 0;
    }
    public List<Customer> queryCusromerByAccount(Customer customer){
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andAccountEqualTo(customer.getAccount());
        return customerMapper.selectByExample(customerExample);
    }
    public List<Customer> queryCustomer(Customer customer){
        customer.setPassword(EncryptBasedDes.encryptBasedDes(customer.getPassword()));
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andAccountEqualTo(customer.getAccount())
        .andPasswordEqualTo(customer.getPassword());
        return customerMapper.selectByExample(customerExample);
    }
}
