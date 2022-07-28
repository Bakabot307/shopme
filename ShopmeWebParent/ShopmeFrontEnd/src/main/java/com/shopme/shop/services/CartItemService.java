/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;

import com.shopme.common.entity.CartItem;
import com.shopme.common.entity.Customer;
import com.shopme.common.exception.ShoppingCartException;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface CartItemService {

    public Integer addProduct(Integer productId, Integer quantity, Customer customer)
            throws ShoppingCartException;

    public List<CartItem> listCartItems(Customer customer);

    float updateQuantity(Integer productId, Integer quantity, Customer customer);

    void removeProduct(Integer productId, Customer customer);

    public void deleteByCustomer(Customer customer);
}
