package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class CategoryRepo {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    public void save(Category category) {
        entityManager.persist(category);
    }
}