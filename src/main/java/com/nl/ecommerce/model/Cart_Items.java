package com.nl.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@EqualsAndHashCode
public class Cart_Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="middle_table_primary_id")
    private Long id;
    @Column
    private Integer quantity;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private Product product;

    public Cart_Items() {
    }

    public Cart_Items( Customer customer, Product product) {
        this.product = product;
        this.customer = customer;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
