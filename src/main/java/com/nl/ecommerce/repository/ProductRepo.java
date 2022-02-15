package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class ProductRepo {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }
}