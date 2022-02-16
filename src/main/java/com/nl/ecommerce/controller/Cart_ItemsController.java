package com.nl.ecommerce.controller;

import com.nl.ecommerce.service.Cart_ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart_items")
public class Cart_ItemsController {
    @Autowired
    private Cart_ItemsService cart_itemsService;

    @PostMapping("/adding/{customerId}/{productId}")
    public ResponseEntity<?> addProductToCustomer (@PathVariable("customerId") Long customerId,
                                                   @PathVariable("productId") Long productId){
        return cart_itemsService.addProductToCustomer(customerId, productId);
    }
}
