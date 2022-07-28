/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.Implements;


import com.shopme.admin.Repository.ProductRepo;
import com.shopme.admin.services.ProductService;
import com.shopme.common.entity.Product;
import com.shopme.common.exception.ProductNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
@Transactional

public class ProductImp implements ProductService {


    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> listProduct() {
        System.out.println("imp" + (List<Product>) productRepo.findAll());
        return (List<Product>) productRepo.findAll();
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setCreatedTime(new Date());
        }

        if (product.getAlias() == null || product.getAlias().isEmpty()) {
            String defaultAlias = product.getName().replaceAll(" ", "-");
            product.setAlias(defaultAlias);
        } else {
            product.setAlias(product.getAlias().replaceAll(" ", "-"));
        }

        product.setUpdatedTime(new Date());

        return productRepo.save(product);
    }

    @Override
    public void delete(Integer id) throws ProductNotFoundException {
        Long countById = productRepo.countById(id);

        if (countById == null || countById == 0) {
            throw new ProductNotFoundException("Could not find any product with ID " + id);
        }

        productRepo.deleteById(id);
    }

    @Override
    public List<Product> listByCategory(Integer categoryId) {
        String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";

        return productRepo.listByCategory(categoryId, categoryIdMatch);

    }

    @Override
    public Product getProduct(String alias) throws ProductNotFoundException {
        Product product = productRepo.findByAlias(alias);
        if (product == null) {
            throw new ProductNotFoundException("Could not find any product with alias " + alias);
        }

        return product;
    }

    @Override
    public Product get(Integer id) throws ProductNotFoundException {
        try {
            return productRepo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ProductNotFoundException("Could not find any product with ID " + id);
        }
    }

    @Override
    public void updateProductEnabledStatus(Integer id, boolean enabled) {
        productRepo.updateEnabledStatus(id, enabled);
    }
    
    @Override
    public Page<Product> search(String keyword, int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULTS_PER_PAGE);
		return productRepo.search(keyword, pageable);
		
	}
}
