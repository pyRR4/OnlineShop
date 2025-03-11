package com.lukaszfabia.main.dto;

import com.lukaszfabia.main.model.Category;
import com.lukaszfabia.main.model.Product;

public record ProductDTO(
        Long id,
        String name,
        Double weight,
        Double price,
        Category category) {

    public ProductDTO(String name, Category category) {
        this(0L, name, 0.0, 0.0, category);
    }

    public ProductDTO(int id, String name, Double weight,
            Double price, Category category) {
        this(Long.valueOf(id), name, weight, price, category);
    }

    public ProductDTO() {
        this(0L, "", 0.0, 0.0, new Category());
    }

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getWeight().doubleValue(), product.getPrice().doubleValue(),
                product.getCategory());
    }

}
