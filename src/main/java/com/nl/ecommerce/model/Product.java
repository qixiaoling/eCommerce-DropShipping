package com.nl.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;
    private String name;
    private String description;
    private int inStock;
    private double price;

    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "product")
    private List<Cart_Items> items = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String description, int inStock,
                   double price ) {
        this.name = name;
        this.description = description;
        this.inStock = inStock;
        this.price = price;

    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonBackReference
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Cart_Items> getItems() {
        return items;
    }

    public void setItems(List<Cart_Items> items) {
        this.items = items;
    }

}
