/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;

import com.shopme.common.entity.Address;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.ShippingRate;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */

public interface ShippingRateService {	
	
	public ShippingRate getShippingRateForCustomer(Customer customer);
	
	public ShippingRate getShippingRateForAddress(Address address);
}
