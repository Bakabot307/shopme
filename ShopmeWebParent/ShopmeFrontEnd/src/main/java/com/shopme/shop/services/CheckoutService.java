/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;

import com.shopme.common.entity.CartItem;
import com.shopme.common.entity.ShippingRate;
import com.shopme.shop.CheckoutInfo;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface CheckoutService {
    public CheckoutInfo prepareCheckout(List<CartItem> cartItems, ShippingRate shippingRate);
}
