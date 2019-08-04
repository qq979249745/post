package com.nchu16201533.post.controller;

import com.nchu16201533.post.bean.CartItem;
import com.nchu16201533.post.bean.Customer;
import com.nchu16201533.post.bean.RestResponse;
import com.nchu16201533.post.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author: 16201533
 * @Date: 2019/6/3 18:38
 * @Version 1.0
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/addToCart")
    @ResponseBody
    public RestResponse addToCart(@Valid CartItem cartItem, BindingResult result, HttpSession session) throws IOException {
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
        }else {
            Object user = session.getAttribute("user");
            if (!(user instanceof Customer)){
                return  RestResponse.fail().add("data","请先登录！");
            }else {
                Customer customer=(Customer)user;
                return cartService.addToCart(cartItem,customer)?RestResponse.success():RestResponse.fail().add("data","添加失败");
            }
        }
    }
    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }
    @PostMapping("/getCartItem")
    @ResponseBody
    public RestResponse getCartItem(HttpSession session){
        Customer customer= (Customer) session.getAttribute("user");
        return RestResponse.success().add("data",cartService.getCartItem(customer));
    }
    @PostMapping("/deleteCartItem")
    @ResponseBody
    public RestResponse deleteCartItem(Integer id){
        return cartService.deleteCartItem(id)?
                RestResponse.success():RestResponse.fail().add("data","删除失败");
    }
}
