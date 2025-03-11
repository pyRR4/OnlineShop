package com.lukaszfabia.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lukaszfabia.main.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}