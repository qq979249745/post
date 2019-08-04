package com.nchu16201533.post.controller;

import com.nchu16201533.post.bean.Customer;
import com.nchu16201533.post.bean.RestResponse;
import com.nchu16201533.post.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: 16201533
 * @Date: 2019/5/28 16:46
 * @Version 1.0
 */
@Controller
public class IndexController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/startRegister")
    @ResponseBody
    public RestResponse startRegister(@Valid Customer customer, BindingResult result){
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
        }else {
            List<Customer> customers = customerService.queryCusromerByAccount(customer);
            if (customers.size()==0){
                return customerService.register(customer)?RestResponse.success():RestResponse.fail().add("data","注册失败");
            }else {
                return RestResponse.fail().add("data","该账号已存在！");
            }
        }
    }

    @PostMapping("/startLogin")
    @ResponseBody
    public RestResponse startLogin(Customer customer, HttpSession session){
            List<Customer> customers = customerService.queryCustomer(customer);
            if (customers.size()==0){
                return RestResponse.fail().add("data","账号或密码错误！");
            }else {
                session.setAttribute("user",customers.get(0));
                return RestResponse.success();
            }
    }
}
