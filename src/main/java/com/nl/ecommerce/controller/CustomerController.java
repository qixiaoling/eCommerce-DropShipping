package com.nl.ecommerce.controller;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.payload.request.SignupRequest;
import com.nl.ecommerce.service.CategoryService;
import com.nl.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getting")
    public ResponseEntity<?> getAllCategory(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/signup")
    @PreAuthorize("hasAnyAuthority('USER','MOD','ADMIN')")
    public ResponseEntity<?> registerCustomer(@RequestBody SignupRequest signupRequest){
        return customerService.registerCustomer(signupRequest);
    }
}
