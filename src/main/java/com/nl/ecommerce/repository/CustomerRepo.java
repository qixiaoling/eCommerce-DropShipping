package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class CustomerRepo {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    public void save(Customer customer) {
        entityManager.persist(customer);
    }
}
