package com.nchu16201533.post.controller;

import com.nchu16201533.post.bean.Manager;
import com.nchu16201533.post.bean.RestResponse;
import com.nchu16201533.post.service.ManagerService;
import com.nchu16201533.post.util.EncryptBasedDes;
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
 * @Date: 2019/5/29 16:46
 * @Version 1.0
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @RequestMapping("/login")
    public String login(){
        return "manager/login";
    }
    @PostMapping("/submit")
    @ResponseBody
    public RestResponse submit(@Valid Manager manager, BindingResult result,HttpSession session){
        if(result.hasErrors()){
            return RestResponse.fail().add("data", result.getAllErrors().get(0).getDefaultMessage());
        } else {
            manager.setPassword(EncryptBasedDes.encryptBasedDes(manager.getPassword()));
            List<Manager> managers = managerService.queeryManager(manager);
            if (managers.size()==1){
                session.setAttribute("user",managers.get(0));
                return RestResponse.success().add("data","categoryManager");
            }else {
                return RestResponse.fail().add("data","账号或密码错误");
            }
        }
    }
}
