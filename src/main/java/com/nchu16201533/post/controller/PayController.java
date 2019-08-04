package com.nchu16201533.post.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.nchu16201533.post.bean.Sale;
import com.nchu16201533.post.config.AlipayConfig;
import com.nchu16201533.post.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: 16201533
 * @Date: 2019/6/6 15:17
 * @Version 1.0
 */
@Controller
public class PayController {
    @Autowired
    private AlipayConfig alipayConfig;
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private SaleService saleService;
    /** 同步地址 */
    @Value("${pay.alipay.returnUrl}")
    private String returnUrl;

    /** 异步地址 */
    @Value("${pay.alipay.returnUrl}")
    private String notifyUrl;


    @PostMapping("/pay")
    public void pay(String payment, String id, HttpServletResponse response) throws AlipayApiException, IOException {
        Sale sale = saleService.getSale(id);
        sale.setPayment(payment);
        if ("支付宝支付".equals(payment)){
            saleService.updateSale(sale);
            aliPay(sale,response);
        }else {
            sale.setStatus("已支付");
            Date date = new Date();
            sale.setPayTime(date);
            sale.setPayNo(String.valueOf(date.getTime()));
            saleService.updateSale(sale);
            response.sendRedirect("/saleInfoPage/"+id);
        }
    }

    private void aliPay(Sale sale,HttpServletResponse response) throws AlipayApiException, IOException {
        AlipayTradePagePayModel model=new AlipayTradePagePayModel();

        // 订单模型
        //AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(sale.getId());
        model.setSubject("XXX超市-软件学院16201533");
        model.setTotalAmount(String.valueOf(sale.getTotalPrice()*0.01));
        model.setBody("感谢你的购买，退款联系软件学院16201533");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setNotifyUrl(notifyUrl);
        alipayTradePagePayRequest.setReturnUrl(returnUrl);
        alipayTradePagePayRequest.setBizModel(model);

        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String form = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        System.out.println(form);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 支付宝页面跳转同步通知页面
     */
    @RequestMapping("/return_url")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        response.setContentType("text/html;charset=UTF-8");

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }

        boolean verifyResult = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), "RSA2");
        //商户订单号
        String saleId = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        if(verifyResult){
            //验证成功
            //请在这里加上商户的业务逻辑程序代码，如保存支付宝交易号

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            //String trade_status = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            System.out.println(saleId);
            System.out.println(trade_no);

            Sale sale = saleService.getSale(saleId);
            sale.setPayNo(trade_no);
            sale.setPayTime(new Date());

            sale.setStatus("已支付");

            saleService.updateSale(sale);

        }
        return "/saleInfoPage/"+saleId;
    }
}
