/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.services;


import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface CustomerService {
    public List<Country> listAllCountries();
    public boolean isEmailUnique(String email);
    public boolean isUsernameUnique(String username);
    void registerCustomer(Customer customer);
    boolean verify(String verificationCode);
}
