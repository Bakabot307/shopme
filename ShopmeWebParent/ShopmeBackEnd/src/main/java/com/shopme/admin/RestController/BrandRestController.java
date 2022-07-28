/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.RestController;


import com.shopme.admin.services.BrandService;
import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.CategoryDto;
import com.shopme.common.exception.BrandNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author gnaht
 */
@RestController
public class BrandRestController {

    @Autowired
    private BrandService service;

    @GetMapping("admin/brand/{id}/categories")
    public List<CategoryDto> listCategoriesByBrand(@PathVariable(name = "id") Integer brandId) throws BrandNotFoundRestException {
        List<CategoryDto> listCategories = new ArrayList<>();

        try {
            Brand brand = service.get(brandId);
            System.out.println("etest" + brand);
            Set<Category> categories = brand.getCategories();

            for (Category category : categories) {
                CategoryDto dto = new CategoryDto(category.getId(), category.getName());
                listCategories.add(dto);
            }

            return listCategories;
        } catch (BrandNotFoundException e) {
            throw new BrandNotFoundRestException();
        }
    }
}
