package com.lukaszfabia.main.service;


import com.lukaszfabia.main.dto.CategoryDTO;
import com.lukaszfabia.main.exceptions.CategoryNotFound;
import com.lukaszfabia.main.model.Category;
import com.lukaszfabia.main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryDTO::new).toList();
    }

    public CategoryDTO getCategoryById(Long id) throws CategoryNotFound {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->  new CategoryNotFound("Category with id " + id + " not found."));

        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO createOrUpdate(CategoryDTO categoryDTO) {

        if (categoryDTO.id() == null) {
            Category savedCategory = categoryRepository.save(new Category(categoryDTO));
            return new CategoryDTO(savedCategory);
        }

        return categoryRepository.findById(categoryDTO.id())
                .map(existingCategory -> {
                    try {
                        existingCategory.setName(categoryDTO.name());
                        existingCategory.setCode(categoryDTO.code());
                    } catch (Exception e) {
                        return new CategoryDTO(existingCategory);
                    }
                    return new CategoryDTO(existingCategory);
                }).orElseGet(() -> {
                    Category newCategory = new Category(categoryDTO);
                    return new CategoryDTO(categoryRepository.save(newCategory));
                });
    }

    @Transactional
    public void deleteCategory(Long id) throws CategoryNotFound {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFound("Category with id " + id + " not found.");
        }
    }
}
