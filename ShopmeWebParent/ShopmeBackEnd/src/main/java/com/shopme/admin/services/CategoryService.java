/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.services;

import com.shopme.common.entity.Category;
import com.shopme.common.exception.CategoryNotFoundException;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface CategoryService {

    List<Category> listCategory(String sortDir);

    public List<Category> listCategoriesUsedInForm();

    Category add(Category category);

    public Category get(Integer id) throws CategoryNotFoundException;

    public String checkUnique(Integer id, String name, String alias);

    public void updateCategoryEnabledStatus(Integer id, boolean enabled);

    public void delete(Integer id) throws CategoryNotFoundException;

    //frontend
    public List<Category> listNoChildrenCategories();

    public Category getCategory(String alias);

    public List<Category> getCategoryParents(Category child);

}
