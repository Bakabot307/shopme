package com.shopme.admin.RestController;

import com.shopme.admin.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gnaht
 */

@RestController
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/admin/category/check_unique")
	public String checkUnique(@Param("id") Integer id, @Param("name") String name,
			@Param("alias") String alias) {
		return categoryService.checkUnique(id, name, alias);
	}
}
