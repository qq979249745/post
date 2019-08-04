package com.nchu16201533.post.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.nchu16201533.post.bean.Customer;
import com.nchu16201533.post.bean.RestResponse;
import com.nchu16201533.post.bean.Sale;
import com.nchu16201533.post.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: 16201533
 * @Date: 2019/6/4 20:27
 * @Version 1.0
 */
@Controller
public class SaleController {
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private SaleService saleService;

    @RequestMapping("/sale")
    public String sale(){
        return "sale";
    }

    @PostMapping("/getAllSales")
    @ResponseBody
    public RestResponse getAllSales(HttpSession session){
        Customer customer= (Customer) session.getAttribute("user");
        List<Sale> allSales = saleService.getAllSales(customer.getId());
        return RestResponse.success().add("data",allSales);
    }

    @PostMapping("/buy")
    @ResponseBody
    public String buy(Integer[] products, HttpSession session){
        Customer customer= (Customer) session.getAttribute("user");
        String sale = saleService.createSale(products, customer.getId());
        return "/saleInfoPage/"+sale;
    }
    @RequestMapping("/saleInfoPage/{id}")
    public String buyInfoPage(@PathVariable(name = "id") String id,Model model){
        model.addAttribute("id",id);
        return "saleInfoPage";
    }
    @RequestMapping("/saleInfo/{id}")
    @ResponseBody
    public RestResponse buyInfo(@PathVariable(name = "id") String id) {

        Sale sale = saleService.getSale(id);
        return RestResponse.success().add("data",sale);
    }
}
