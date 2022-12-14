/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.controller;

import com.shopme.admin.Utils.FileUploadUtil;
import com.shopme.admin.services.BrandService;
import com.shopme.admin.services.CategoryService;
import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;
import com.shopme.common.exception.BrandNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/brand")
    public String index(Model model) {
        List<Brand> brands = brandService.findAll();
        model.addAttribute("brands", brands);
        return "brand/brand";
    }

    @GetMapping("/brand/new")
    public String newBrand(Model model) {
        List<Category> listCategories = categoryService.listCategoriesUsedInForm();

        model.addAttribute("listCategories", listCategories);
        model.addAttribute("brand", new Brand());
        model.addAttribute("pageTitle", "Create New Brand");

        return "brand/brand_form";
    }

    @PostMapping("/brand/save")
    public String saveBrand(Brand brand, @RequestParam("fileImage") MultipartFile multipartFile,
            RedirectAttributes ra) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            brand.setLogo(fileName);

            Brand savedBrand = brandService.save(brand);
            String uploadDir = "../brand-logos/" + savedBrand.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } else {
            brandService.save(brand);
        }

        ra.addFlashAttribute("message", "The brand has been saved successfully.");
        return "redirect:/admin/brand";
    }

    @GetMapping("/brand/edit/{id}")
    public String editBrand(@PathVariable(name = "id") Integer id, Model model,
            RedirectAttributes ra) {
        try {
            Brand brand = brandService.get(id);
            List<Category> listCategories = categoryService.listCategoriesUsedInForm();

            model.addAttribute("brand", brand);
            model.addAttribute("listCategories", listCategories);
            model.addAttribute("pageTitle", "Edit Brand (ID: " + id + ")");
            return "brand/brand_form";
        } catch (BrandNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/admin/brand";
        }
    }

    @GetMapping("/brand/delete/{id}")
    public String deleteBrand(@PathVariable(name = "id") Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            brandService.delete(id);
            String brandDir = "../brand-logos/" + id;
            FileUploadUtil.removeDir(brandDir);

            redirectAttributes.addFlashAttribute("message",
                    "The brand ID " + id + " has been deleted successfully");
        } catch (BrandNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }

        return "redirect:/admin/brand";
    }
}
