package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {

    Optional<Customer> findById (Long customerId);

}
