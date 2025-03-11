package com.lukaszfabia.main.repository;

import com.lukaszfabia.main.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
