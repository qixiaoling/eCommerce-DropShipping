package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Cart_Composite_ID;
import com.nl.ecommerce.model.Cart_Items;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Cart_ItemsRepository extends JpaRepository <Cart_Items, Long> {

    List<Cart_Items> findByCustomer(Customer customer);


    Cart_Items findByCustomerAndProduct(Customer customer, Product product);

    @Override
    void deleteById (Long id);
}
