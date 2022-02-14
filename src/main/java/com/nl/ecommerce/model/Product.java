package com.nl.ecommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
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
    @OneToMany (mappedBy = "product")
    private List<Cart_Items> items;

    public Product(Long productId, String name, String description, int inStock,
                   double price, Category category, List<Cart_Items> items) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.inStock = inStock;
        this.price = price;
        this.category = category;
        this.items = items;
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
