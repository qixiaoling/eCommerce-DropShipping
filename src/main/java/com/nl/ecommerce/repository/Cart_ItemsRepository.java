package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Cart_Composite_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_ItemsRepository extends JpaRepository < Cart_ItemsRepository, Cart_Composite_ID> {
    @Override
    void deleteById (Cart_Composite_ID id);
}
