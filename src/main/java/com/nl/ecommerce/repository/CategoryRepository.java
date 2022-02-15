package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {
    Optional < Category > findByCategoryId (Long categoryId);
}