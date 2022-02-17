package com.nl.ecommerce.controller;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Product;
import com.nl.ecommerce.service.ProductService;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
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
    @GetMapping("/getting/{productId}")
    public Product getProductById(@PathVariable("productId") Long productId){
        return productService.getProductById(productId);
    }
    @PutMapping("/updating/{productId}")
    public ResponseEntity<?> updateProductById(@PathVariable("productId") Long productId, Product aNewProduct){
        return productService.updateProductById(productId, aNewProduct);
    }
    @DeleteMapping("/deleting/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable("productId") Long productId){
        return productService.deleteProductById(productId);
    }

}
