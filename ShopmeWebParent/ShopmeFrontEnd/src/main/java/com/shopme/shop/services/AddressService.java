/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;

import com.shopme.common.entity.Address;
import com.shopme.common.entity.Customer;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface AddressService {

    public List<Address> listAddressBook(Customer customer);

    public void save(Address address);

    public Address get(Integer addressId, Integer customerId);

    public void delete(Integer addressId, Integer customerId);

    public void setDefaultAddress(Integer defaultAddressId, Integer customerId);

    public Address getDefaultAddress(Customer customer);
}
