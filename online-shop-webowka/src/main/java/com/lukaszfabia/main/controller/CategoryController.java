package com.lukaszfabia.main.controller;


import com.lukaszfabia.main.dto.CategoryDTO;
import com.lukaszfabia.main.dto.ProductDTO;
import com.lukaszfabia.main.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categoriesDTO", categoryService.getAllCategories());
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("body", "category/list");

        return "layout/layout";
    }

    @PostMapping
    public String createCategory(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.createOrUpdate(categoryDTO);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            CategoryDTO categoryDTO = categoryService.getCategoryById(id);
            model.addAttribute("categoryDTO", categoryDTO);
            model.addAttribute("body", "category/edit");
        } catch (Exception e) {
            model.addAttribute("body", "notFound");
        }
        return "layout/layout";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryDTO categoryDTO, Model model) {
        CategoryDTO categoryCopy = new CategoryDTO(id, categoryDTO.name(), categoryDTO.code());

        try {
            categoryService.createOrUpdate(categoryCopy);
        } catch (Exception e) {
            model.addAttribute("body", "notFound");
            return "layout/layout";
        }

        return "redirect:/admin/categories";
    }

    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.deleteCategory(id);
            return "redirect:/admin/categories";
        } catch (Exception e) {
            model.addAttribute("body", "notFound");
            return "layout/layout";
        }
    }
}
