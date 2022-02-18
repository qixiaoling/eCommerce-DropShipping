package com.nl.ecommerce.controller;

import com.nl.ecommerce.model.Cart_Items;
import com.nl.ecommerce.service.Cart_ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart_items")
public class Cart_ItemsController {
    @Autowired
    private Cart_ItemsService cart_itemsService;

    @GetMapping("/getting/{customerId}")
    public ResponseEntity<?> getAllCartItemsByCustomerId (@PathVariable("customerId") Long customerId){
        return cart_itemsService.getAllCartItemsByCustomerId(customerId);
    }

    @PostMapping("/adding/{customerId}/{productId}")
    public ResponseEntity<?> addProductToCustomer (@PathVariable("customerId") Long customerId,
                                                   @PathVariable("productId") Long productId){
        return cart_itemsService.addProductToCustomer(customerId, productId);
    }
    @PutMapping("/addingQuantity/{customerId}/{productId}")
    public ResponseEntity<?> addQuantity(@PathVariable("customerId") Long customerId,
                                         @PathVariable("productId") Long productId,
                                         @RequestBody Cart_Items singleCI){
       return cart_itemsService.addQuantity(customerId, productId, singleCI);

    }
    @DeleteMapping("/deleting/{customerId}/{productId}")
    public ResponseEntity<?> deleteCart_ItemById(@PathVariable("customerId") Long customerId,
                                                 @PathVariable("productId") Long productId){
        return cart_itemsService.deleteCart_ItemById(customerId, productId);
    }
}
