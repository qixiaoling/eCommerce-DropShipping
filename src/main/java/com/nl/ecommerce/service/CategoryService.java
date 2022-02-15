package com.nl.ecommerce.service;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public ResponseEntity<?> getAllCategory(){
        List<Category> categoryList = new ArrayList<>();
        categoryRepository.findAll().forEach(categoryList::add);
        return ResponseEntity.ok().body(categoryList);
    }
    public ResponseEntity<?> addCategory(Category category) {
        if(!(category.getName() == null)){
            if(categoryRepository.existsByCategoryId(category.getCategoryId()).equals(Boolean.FALSE)){
                categoryRepository.save(category);
                return ResponseEntity.ok().body("The category is now added.");
            }else{
                return ResponseEntity.badRequest().body("Error, this category already exists.");

            }
        }
        return ResponseEntity.badRequest().body("Please fill in the category name.");

    }
    public Category getCategoryById(Long categoryId){
        Optional<Category> possibleCategory = categoryRepository.findById(categoryId);
        if(possibleCategory.isPresent()){
            return possibleCategory.get();
        }
        return null;
    }
    public ResponseEntity<?> updateCategoryById(Long categoryId, Category category){
        Optional<Category> possibleCategory = categoryRepository.findById(categoryId);
        if(possibleCategory.isPresent()){
           possibleCategory.get().setName(category.getName());
            return ResponseEntity.ok().body("The category is successfully updated.");
        }
        return ResponseEntity.badRequest().body("Error, this category does not exist.");

    }
    public ResponseEntity<?> deleteCategoryById(Long categoryId) {
        Optional<Category> possibleCategory = categoryRepository.findById(categoryId);
        if(possibleCategory.isPresent()){
            categoryRepository.deleteById(categoryId);
            return ResponseEntity.ok().body("The category is deleted successfully.");
        }
        return ResponseEntity.badRequest().body("Please check the category id again.");


    }
}
