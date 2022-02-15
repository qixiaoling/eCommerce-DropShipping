package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Cart_Items;
import com.nl.ecommerce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class Cart_itemsRepo {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    public void save(Cart_Items cart_items) {
        entityManager.persist(cart_items);
    }
}