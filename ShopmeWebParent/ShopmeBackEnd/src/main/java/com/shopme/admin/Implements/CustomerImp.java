/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.Implements;

import com.shopme.admin.Repository.CountryRepo;
import com.shopme.admin.Repository.CustomerRepo;
import com.shopme.admin.services.CustomerService;
import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
@Transactional
public class CustomerImp implements CustomerService {

    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Country> listAllCountries() {
        return countryRepo.findAllByOrderByNameAsc();
    }

    @Override
    public boolean isEmailUnique(String email) {
        System.out.println("email" + email);
        Customer userByEmail = customerRepo.findByEmail(email);

        if (userByEmail == null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isUsernameUnique(String username) {
        System.out.println("username" + username);
        Customer userByEmail = customerRepo.findByUsername(username);

        if (userByEmail == null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void registerCustomer(Customer customer) {
        encodePassword(customer);
        customer.setEnabled(false);
        customer.setCreatedTime(new Date());

        String randomCode = RandomString.make(64);
        customer.setVerificationCode(randomCode);

        customerRepo.save(customer);

    }

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

    @Override
    public boolean verify(String verificationCode) {
        Customer customer = customerRepo.findByVerificationCode(verificationCode);
        System.out.println("customercode" + customer.getVerificationCode());
        if (customer == null || customer.isEnabled()) {
            return false;
        } else {
            customerRepo.enable(customer.getId());
            return true;
        }
    }
}
