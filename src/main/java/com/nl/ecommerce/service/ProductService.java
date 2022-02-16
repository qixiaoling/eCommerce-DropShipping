package com.nl.ecommerce.service;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Product;
import com.nl.ecommerce.payload.response.MessageResponse;
import com.nl.ecommerce.repository.CategoryRepository;
import com.nl.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductService (ProductRepository productRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return ResponseEntity.ok().body(productList);
    }

    public Product getProductById(Long productId) {
        Product product = productRepository.findByProductId(productId);
        return product;
    }
    public ResponseEntity<?> addNewProductToCategory(Long categoryId, Product product) {
        Optional<Category> categoryFromDB = categoryRepository.findById(categoryId);
        if(categoryFromDB.isPresent()){
            product.setCategory(categoryFromDB.get());
            productRepository.save(product);
            return ResponseEntity.ok().body(new MessageResponse("Product is added."));
        }
        return ResponseEntity.badRequest().body("Error: category does not exist.");
    }
    public ResponseEntity<?> updateProductById(Long productId, Product aNewProduct){
        Optional<Product> possibleProduct = productRepository.findById(productId);
        if(possibleProduct.isPresent()){
            possibleProduct.get().setName(aNewProduct.getName());
            possibleProduct.get().setDescription(aNewProduct.getDescription());
            possibleProduct.get().setInStock(aNewProduct.getInStock());
            possibleProduct.get().setPrice(aNewProduct.getPrice());
            productRepository.save(possibleProduct.get());
            return ResponseEntity.ok().body("The product is updated successfully");
        }
        return ResponseEntity.badRequest().body("Please check the product id again.");
    }
    public ResponseEntity<?> deleteProductById(Long productId) {
        Optional<Product> possibleProduct = productRepository.findById(productId);
        if(possibleProduct.isPresent()){
            productRepository.deleteById(productId);
            return ResponseEntity.ok().body("This product is deleted successfully.");
        }
        return ResponseEntity.badRequest().body("Error, please check the product id again.");
    }

}

