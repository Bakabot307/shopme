/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.Implements;

import com.shopme.admin.Repository.BrandRepo;
import com.shopme.admin.services.BrandService;
import com.shopme.common.entity.Brand;
import com.shopme.common.exception.BrandNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
@Transactional
public class BrandImp implements BrandService {
    
    @Autowired
    private BrandRepo brandRepo;
    @Override
    public List<Brand> findAll() {
        return (List<Brand>) brandRepo.findAll();
    }
    public Brand get(Integer id) throws BrandNotFoundException {
		try {
			return (Brand) brandRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new BrandNotFoundException("Could not find any brand with ID " + id);
		}
	}
    @Override
    public Brand save(Brand brand) {
		return brandRepo.save(brand);
    }
	
	public void delete(Integer id) throws BrandNotFoundException {
		Long countById = brandRepo.countById(id);
		
		if (countById == null || countById == 0) {
			throw new BrandNotFoundException("Could not find any brand with ID " + id);			
		}
		
		brandRepo.deleteById(id);
	}
}
