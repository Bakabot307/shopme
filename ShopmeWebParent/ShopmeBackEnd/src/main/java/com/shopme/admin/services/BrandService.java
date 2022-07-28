/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.services;



import com.shopme.common.entity.Brand;
import com.shopme.common.exception.BrandNotFoundException;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface BrandService {
     List<Brand> findAll();
     Brand get(Integer id) throws BrandNotFoundException;
     public void delete(Integer id) throws BrandNotFoundException;
     public Brand save(Brand brand);
}
