package com.lukaszfabia.main.controller;

import com.lukaszfabia.main.dto.CategoryDTO;
import com.lukaszfabia.main.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.lukaszfabia.main.dto.ProductDTO;
import com.lukaszfabia.main.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("productsDTO", productService.getAllProducts());
        model.addAttribute("categoriesDTO", categoryService.getAllCategories());
        model.addAttribute("productDTO", new ProductDTO());

        // jesli tak trzeba robic to XD
        model.addAttribute("body", "product/list");
        return "layout/layout";
    }

    @PostMapping
    public String createProduct(@ModelAttribute ProductDTO productDTO) {
        productService.createOrUpdate(productDTO);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("productDTO", productService.getProductById(id));
            model.addAttribute("body", "product/details");
        } catch (Exception e) {
            model.addAttribute("body", "notFound");
        }
        return "layout/layout";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            ProductDTO productDTO = productService.getProductById(id);
            List<CategoryDTO> categories = categoryService.getAllCategories();
            model.addAttribute("productDTO", productDTO);
            model.addAttribute("categoriesDTO", categories);
            model.addAttribute("body", "product/edit");
        } catch (Exception e) {
            model.addAttribute("body", "notFound");
        }
        return "layout/layout";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO productDTO, Model model) {
        ProductDTO copy = new ProductDTO(id, productDTO.name(), productDTO.weight(), productDTO.price(),
                productDTO.category());

        try {
            productService.createOrUpdate(copy);
        } catch (Exception e) {
            model.addAttribute("body", "notFound");
            System.out.println(e.getMessage());
            return "layout/layout";
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        try {
            productService.deleteProduct(id);
            return "redirect:/admin/products";
        } catch (Exception e) {
            model.addAttribute("body", "notFound");
            return "layout/layout";
        }
    }
}
