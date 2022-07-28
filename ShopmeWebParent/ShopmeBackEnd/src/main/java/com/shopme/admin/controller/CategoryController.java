/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.controller;

import com.shopme.admin.Utils.FileUploadUtil;
import com.shopme.admin.services.CategoryService;
import com.shopme.common.entity.Category;
import com.shopme.common.exception.CategoryNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author gnaht
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*
	 * @RequestParam dùng để đánh dấu một biến là request param trong request gửi
	 * lên server.
	 * Nó sẽ gán dữ liệu của param-name tương ứng vào biến
     */
    @GetMapping("/category")
    public String index(@Param("sort") String sort, Model model) {
        // Trả về đối tượng todoList.
      	if (sort ==  null || sort.isEmpty()) {
			sort = "asc";
		}
		
		List<Category> listCategories = categoryService.listCategory(sort);
		
		String reverseSortDir = sort.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("categories", listCategories);
		model.addAttribute("reverseSortDir", reverseSortDir);

        // Trả về template "listTodo.html"
        return "/category/category";
    }

    @GetMapping("/category/new")
    public String newCategory(Model model) {
        List<Category> listCategories = categoryService.listCategoriesUsedInForm();
        System.out.println(listCategories);
        model.addAttribute("category", new Category());
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("pageTitle", "Create New Category");

        return "category/category_form";
    }

    @PostMapping("/category/new")
    public String saveCategory(Category category,
            @RequestParam("images") MultipartFile multipartFile,
            RedirectAttributes ra) throws IOException {
        if (!multipartFile.isEmpty()) {
            System.out.println("postmapping");
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            System.out.println("filename" + fileName);
            category.setImage(fileName);

            Category savedCategory = categoryService.add(category);
            String uploadDir = "../category-images/" + savedCategory.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            if (category.getImage() == null) {
                category.setImage(null);
            }
            categoryService.add(category);
        }
        ra.addFlashAttribute("message", "The category has been saved successfully.");
        return "redirect:/admin/category";
    }

    @GetMapping("/category/edit/{id}")
    public String editCategory(@PathVariable(name = "id") Integer id, Model model,
            RedirectAttributes ra) {
        try {
            Category category = categoryService.get(id);
            List<Category> listCategories = categoryService.listCategoriesUsedInForm();
            System.out.println("category controller" + listCategories.get(0).getId());
            model.addAttribute("category", category);
            model.addAttribute("listCategories", listCategories);
            model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");

            return "category/category_form";
        } catch (CategoryNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/admin/category";
        }
    }

    @GetMapping("/category/{id}/enabled/{status}")
    public String updateCategoryEnabledStatus(@PathVariable("id") Integer id,
            @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
        categoryService.updateCategoryEnabledStatus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message = "The category ID " + id + " has been " + status;
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/admin/category";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
            String categoryDir = "../category-images/" + id;
            FileUploadUtil.removeDir(categoryDir);

            redirectAttributes.addFlashAttribute("message",
                    "The category ID " + id + " has been deleted successfully");
        } catch (CategoryNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }

        return "redirect:/admin/category";
    }
}
