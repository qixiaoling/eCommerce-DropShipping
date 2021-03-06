package com.nl.ecommerce.service;

import com.nl.ecommerce.model.Cart_Items;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.model.Product;
import com.nl.ecommerce.repository.Cart_ItemsRepository;
import com.nl.ecommerce.repository.CustomerRepository;
import com.nl.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class Cart_ItemsService {
    @Autowired
    private Cart_ItemsRepository cart_itemsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart_ItemsService(Cart_ItemsRepository cart_itemsRepository,
                             CustomerRepository customerRepository,
                             ProductRepository productRepository) {
        this.cart_itemsRepository = cart_itemsRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> getAllCartItemsByCustomerId(Long customerId){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            List<Cart_Items> cart_items = new ArrayList<>();
            cart_items.addAll(cart_itemsRepository.findByCustomer(possibleCustomer.get()));
            return ResponseEntity.ok().body(cart_items);
        }else{
            return ResponseEntity.badRequest().body("Error, customer does not exist.");
        }
    }


//    public ResponseEntity<?> addProduct(Long productId, Integer quantity, Long customerId){
//
//        Product product = productRepository.findByProductId(productId);
//        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
//        if(possibleCustomer.isPresent()){
//            Cart_Items cart_item = cart_itemsRepository.findByCustomerAndProduct(possibleCustomer.get(), product);
//            if(cart_item != null){
//                Integer addedQuantity = cart_item.getQuantity() + quantity;
//                cart_item.setQuantity(addedQuantity);
//            }else {
//                cart_item.setCustomer(possibleCustomer.get());
//                cart_item.setProduct(product);
//                cart_item.setQuantity(quantity);
//            }
//
//            cart_itemsRepository.save(cart_item);
//            return ResponseEntity.ok().body("The product is now successfully added");
//        }
//        return ResponseEntity.badRequest().body("The customer does not exist.");
//    }
        public ResponseEntity<?> addProductToCustomer(Long customerId, Long productId){

        Product product = productRepository.findByProductId(productId);
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            Cart_Items cart_item = cart_itemsRepository.findByCustomerAndProduct(possibleCustomer.get(), product);
            if(cart_item != null){
                return ResponseEntity.badRequest().body("this product is already added to cart.");
            }else {
                Cart_Items cart_item_added = new Cart_Items(possibleCustomer.get(), product);
                cart_item_added.setCustomer(possibleCustomer.get());
                cart_item_added.setProduct(product);
                cart_itemsRepository.save(cart_item_added);
                return ResponseEntity.ok().body("The product is now successfully added");
            }
        }
        return ResponseEntity.badRequest().body("The customer does not exist.");
    }
        public ResponseEntity<?> addQuantity(Long customerId, Long productId, Cart_Items singleCI) {

            Product product = productRepository.findByProductId(productId);
            Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
            if (possibleCustomer.isPresent()) {
                Cart_Items cart_item = cart_itemsRepository.findByCustomerAndProduct(possibleCustomer.get(), product);
                cart_item.setQuantity(singleCI.getQuantity());
                cart_itemsRepository.save(cart_item);
                return ResponseEntity.ok().body("Quantity has been added.");
            }
            return ResponseEntity.badRequest().body("Please check the customer id.");
        }

        public ResponseEntity<?> deleteCart_ItemById (Long customerId, Long productId){
            Product product = productRepository.findByProductId(productId);
            Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
            if (possibleCustomer.isPresent()) {
                cart_itemsRepository.deleteByCustomerAndProduct(possibleCustomer.get(), product);
                return ResponseEntity.ok().body("This item is now deleted.");
            }
            return ResponseEntity.badRequest().body("Please check the customer id.");
        }






}
