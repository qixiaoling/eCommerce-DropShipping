package com.nl.ecommerce.controller;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.repository.CategoryRepository;
import com.nl.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @PostMapping("/categories")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

}
