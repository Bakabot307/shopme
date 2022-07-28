/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;

import com.shopme.common.entity.Category;
import com.shopme.common.exception.CategoryNotFoundException;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface CategoryService {

    //frontend
    public List<Category> listNoChildrenCategories();

    public Category getCategory(String alias);

    public List<Category> getCategoryParents(Category child);

}
