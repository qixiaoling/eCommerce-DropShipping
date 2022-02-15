package com.nl.ecommerce.controller;

import com.nl.ecommerce.model.Category;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.service.CategoryService;
import com.nl.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping("/getting")
    public ResponseEntity<?> getAllCategory(){
        return customerService.getAllCustomers();
    }
    @PostMapping("/adding")
    public ResponseEntity<?> addCategory(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
}
