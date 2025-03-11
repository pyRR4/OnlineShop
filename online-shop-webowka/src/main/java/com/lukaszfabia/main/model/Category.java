package com.lukaszfabia.main.model;

import com.lukaszfabia.main.dto.CategoryDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Category(CategoryDTO categoryDTO) {
        this.name = categoryDTO.name();
        this.code = categoryDTO.code();
    }

    public Category() {
        this(new CategoryDTO());
    }

    // MARK: getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    // MARK: setters

    public void setCode(String code) throws Exception {
        if (code.isEmpty()) {
            throw new Exception("Code is empty!");
        } else {
            this.code = code;
        }
    }

    public void setName(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Name is empty!");
        } else {
            this.name = name;
        }
    }
}
