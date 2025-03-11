package com.lukaszfabia.main.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lukaszfabia.main.dto.ProductDTO;
import com.lukaszfabia.main.exceptions.ProductNotFound;
import com.lukaszfabia.main.model.Product;
import com.lukaszfabia.main.repository.ProductRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct);
    }

    public ProductDTO getProductById(Long id) throws ProductNotFound {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Product not found with id " + id));
        return new ProductDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDTO::new).toList();
    }

    @Transactional
    public void deleteProduct(Long id) throws ProductNotFound {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFound("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductDTO createOrUpdate(ProductDTO productDTO) {
        Product product;

        if (productDTO.id() == null) {
            Product savedProduct = productRepository.save(new Product(productDTO));

            return new ProductDTO(savedProduct);
        }

        Optional<Product> existingProduct = productRepository.findById(productDTO.id());

        if (existingProduct.isPresent()) {
            product = existingProduct.get();
            try {
                product.setName(productDTO.name());
                product.setCategory(productDTO.category());
                product.setPrice(BigDecimal.valueOf(productDTO.price()));
                product.setWeight(BigDecimal.valueOf(productDTO.weight()));
            } catch (Exception e) {
                return new ProductDTO(product);
            }
        } else {
            product = new Product(productDTO);
        }

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct);
    }
}
