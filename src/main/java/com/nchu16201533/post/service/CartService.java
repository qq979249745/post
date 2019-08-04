package com.nchu16201533.post.service;

import com.nchu16201533.post.bean.*;
import com.nchu16201533.post.dao.CartItemMapper;
import com.nchu16201533.post.dao.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 16201533
 * @Date: 2019/6/3 21:44
 * @Version 1.0
 */
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private ProductService productService;

    public boolean addToCart(CartItem cartItem, Customer customer) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andCustomerIdEqualTo(customer.getId());
        Cart cart = cartMapper.selectByExample(cartExample).get(0);
        cartItem.setCartId(cart.getId());
        List<CartItem> cartItems = getCartItems(cart.getId(), cartItem.getProductId());
        if (cartItems.size()>0){
            cartItem.setId(cartItems.get(0).getId());
            cartItem.setQuanity(cartItems.get(0).getQuanity()+cartItem.getQuanity());
            return cartItemMapper.updateByPrimaryKey(cartItem) > 0;
        }else
        return cartItemMapper.insert(cartItem)>0;

    }

    public List<CartItem> getCartItems(Integer cartId, String productId){
        CartItemExample cartItemExample = new CartItemExample();
        cartItemExample.createCriteria().andCartIdEqualTo(cartId).andProductIdEqualTo(productId);
        return cartItemMapper.selectByExample(cartItemExample);
    }

    public List<CartItem> getCartItem(Customer customer) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andCustomerIdEqualTo(customer.getId());
        Cart cart = cartMapper.selectByExample(cartExample).get(0);
        CartItemExample cartItemExample=new CartItemExample();
        cartItemExample.createCriteria().andCartIdEqualTo(cart.getId());
        List<CartItem> cartItems = cartItemMapper.selectByExample(cartItemExample);
        for (CartItem cartItem : cartItems) {
            cartItem.setProduct(productService.getProductById(cartItem.getProductId()));
        }
        return  cartItems;
    }

    public boolean deleteCartItem(Integer id) {
        return cartItemMapper.deleteByPrimaryKey(id) > 0;
    }
}
