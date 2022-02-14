package com.nl.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@EqualsAndHashCode
public class Cart_Items {
    @EmbeddedId
    private Cart_Composite_ID id;
    @Column
    private Integer quantity;

    @MapsId("customerid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @MapsId("productid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Cart_Items() {
    }

    public Cart_Items(Cart_Composite_ID id, Integer quantity, Customer customer, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.customer = customer;
        this.product = product;
    }

    public Cart_Composite_ID getId() {
        return id;
    }

    public void setId(Cart_Composite_ID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
