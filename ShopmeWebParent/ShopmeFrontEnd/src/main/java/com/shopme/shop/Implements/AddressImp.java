/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.Implements;

import com.shopme.common.entity.Address;
import com.shopme.common.entity.Customer;
import com.shopme.shop.Repository.AddressRepo;
import com.shopme.shop.services.AddressService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
@Transactional
public class AddressImp implements AddressService {

    @Autowired
    private AddressRepo repo;

    @Override
    public List<Address> listAddressBook(Customer customer) {
        return repo.findByCustomer(customer);
    }

    @Override
    public void save(Address address) {
        repo.save(address);
    }

    @Override
    public Address get(Integer addressId, Integer customerId) {
        return repo.findByIdAndCustomer(addressId, customerId);
    }

    @Override
    public void delete(Integer addressId, Integer customerId) {
        repo.deleteByIdAndCustomer(addressId, customerId);
    }

    @Override
    public void setDefaultAddress(Integer defaultAddressId, Integer customerId) {
        if (defaultAddressId > 0) {
            repo.setDefaultAddress(defaultAddressId);
        }

        repo.setNonDefaultForOthers(defaultAddressId, customerId);
    }

    @Override
    public Address getDefaultAddress(Customer customer) {
        return repo.findDefaultByCustomer(customer.getId());
    }
}
