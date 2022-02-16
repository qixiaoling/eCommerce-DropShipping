package com.nl.ecommerce.controller;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Product;
import com.nl.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getting")
    public ResponseEntity<?> getAllProduct() {
        return productService.getAllProduct();
    }
    @PostMapping("/adding/{name}")
    public ResponseEntity<?> addNewProductToCategory(@PathVariable ("name") String name,
                                                     @RequestBody Product product){
        return productService.addNewProductToCategory(name, product);
    }

}
