/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.Implements;


import com.shopme.shop.Repository.CategoryRepo;
import com.shopme.shop.services.CategoryService;
import com.shopme.common.entity.Category;
import com.shopme.common.exception.CategoryNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
@Transactional
public class CategoryImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    
    //frontend
    @Override
    public List<Category> listNoChildrenCategories() {
		List<Category> listNoChildrenCategories = new ArrayList<>();
		
		List<Category> listEnabledCategories = categoryRepo.findAllEnabled();
		
		listEnabledCategories.forEach(category -> {
			Set<Category> children = category.getChildren();
			if (children == null || children.size() == 0) {
				listNoChildrenCategories.add(category);
			}
		});
		        System.out.println("parent" + listNoChildrenCategories);
		return listNoChildrenCategories;
	}

    @Override
    public Category getCategory(String alias) {
        return categoryRepo.findByAliasEnabled(alias);
    }

    @Override
    public List<Category> getCategoryParents(Category child) {
        List<Category> listParents = new ArrayList<>();

        Category parent = child.getParent();

        while (parent != null) {
            listParents.add(0, parent);
            parent = parent.getParent();
        }

        listParents.add(child);
        return listParents;
    }
}
