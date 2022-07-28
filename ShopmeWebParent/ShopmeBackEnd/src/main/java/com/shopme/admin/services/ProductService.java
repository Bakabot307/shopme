/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.services;


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
    List<Product> listProduct();

    Product save(Product product);

    void delete(Integer id) throws ProductNotFoundException;

    List<Product> listByCategory(Integer categoryId);

    Product getProduct(String alias) throws ProductNotFoundException;

    Product get(Integer id) throws ProductNotFoundException;
    
    void updateProductEnabledStatus(Integer id, boolean enabled);

    Page<Product> search(String keyword, int pageNum);
}
