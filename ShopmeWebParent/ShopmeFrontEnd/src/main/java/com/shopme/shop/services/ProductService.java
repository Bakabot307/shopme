/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;


import com.shopme.common.entity.Product;
import com.shopme.common.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author gnaht
 */
public interface ProductService {
    public static final int PRODUCTS_PER_PAGE = 10;
    public static final int SEARCH_RESULTS_PER_PAGE = 10;

    List<Product> listByCategory(Integer categoryId);
    List<Product> findAll();

    Product getProduct(String alias) throws ProductNotFoundException;  

    Page<Product> search(String keyword, int pageNum);
}
