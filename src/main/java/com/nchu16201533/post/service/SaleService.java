package com.nchu16201533.post.service;

import com.nchu16201533.post.bean.*;
import com.nchu16201533.post.dao.CartItemMapper;
import com.nchu16201533.post.dao.SaleItemMapper;
import com.nchu16201533.post.dao.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 16201533
 * @Date: 2019/6/4 21:02
 * @Version 1.0
 */
@Service
public class SaleService {
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private SaleItemMapper saleItemMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private ProductService productService;

    public String createSale(Integer[] products, Integer id) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Sale sale = new Sale();
        sale.setId(uuid);
        sale.setCustomerId(id);
        sale.setCreateTime(new Date());
        sale.setStatus("待付款");
        saleMapper.insertSelective(sale);
        for (Integer product : products) {
            CartItem cartItem = cartItemMapper.selectByPrimaryKey(product);
            SaleItem saleItem = new SaleItem();
            saleItem.setSaleId(uuid);
            saleItem.setProductId(cartItem.getProductId());
            saleItem.setQuanity(cartItem.getQuanity());
            saleItemMapper.insert(saleItem);
        }
        return uuid;
    }

    public Sale getSale(String id) {
        Sale sale = saleMapper.selectByPrimaryKey(id);
        sale.setSaleItems(getSaleItems(id));
        return sale;
    }
    public List<SaleItem> getSaleItems(String id){
        SaleItemExample saleItemExample = new SaleItemExample();
        saleItemExample.createCriteria().andSaleIdEqualTo(id);
        List<SaleItem> saleItems = saleItemMapper.selectByExample(saleItemExample);
        for (SaleItem saleItem : saleItems) {
            saleItem.setProduct(productService.getProductById(saleItem.getProductId()));
        }
        return saleItems;
    }

    public List<Sale> getAllSales(Integer id) {
        SaleExample saleExample = new SaleExample();
        saleExample.createCriteria().andCustomerIdEqualTo(id);
        return saleMapper.selectByExample(saleExample);
    }

    public void updateSale(Sale sale) {
        saleMapper.updateByPrimaryKeySelective(sale);
    }
}
