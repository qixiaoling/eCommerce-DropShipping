package com.nl.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
@Table
@Entity
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;
    private String name;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
