package com.lukaszfabia.main.controller;

import com.lukaszfabia.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {


    private final ProductService productService;

    @Autowired
    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/user/shop")
    public String showProducts(Model model) {
        model.addAttribute("productsDTO", productService.getAllProducts());
        model.addAttribute("body", "shop/shop");
        return "layout/user_layout";
    }

    @GetMapping("/user/cart")
    public String showCart(Model model) {
        model.addAttribute("body", "shop/cart");

        return "layout/user_layout";
    }
}
