package com.nl.ecommerce.controller;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.repository.CategoryRepository;
import com.nl.ecommerce.service.CategoryService;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/getting")
    public ResponseEntity<?> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PostMapping("/adding")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/getting/{name}")
    public Category getCategoryById(@PathVariable("name") String name){
        return categoryService.getCategoryById(name);
    }
    @PutMapping("/updating/{name}")
    public ResponseEntity<?> updateCategoryById(@PathVariable("name") String name, Category category){
        return categoryService.updateCategoryById(name, category);
    }
    @DeleteMapping("/deleting/{name}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("name") String name){
        return categoryService.deleteCategoryById(name);
    }
}
