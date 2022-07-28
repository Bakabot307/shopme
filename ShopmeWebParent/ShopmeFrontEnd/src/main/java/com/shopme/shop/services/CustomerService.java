/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;


import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;
import com.shopme.common.exception.CustomerNotFoundException;
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
    void update(Customer customerInForm);
    Customer getByResetPasswordToken(String token);
    void updatePassword(String token, String newPassword) throws CustomerNotFoundException;
    String updateResetPasswordToken(String email) throws CustomerNotFoundException;
    Customer getCustomerByEmail(String email);
}
