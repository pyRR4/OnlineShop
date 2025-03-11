package com.lukaszfabia.main.dto;

import com.lukaszfabia.main.model.Category;

public record CategoryDTO(
        Long id,
        String name,
        String code
) {
    public CategoryDTO(int id, String name, String code) {
        this(Long.valueOf(id), name, code);
    }

    public CategoryDTO() {
        this(0L, "", "");
    }

    public CategoryDTO(Category category) {
        this(category.getId(), category.getName(), category.getCode());
    }
}
