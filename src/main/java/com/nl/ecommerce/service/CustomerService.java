package com.nl.ecommerce.service;

import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return ResponseEntity.ok().body(customers);
    }
    public ResponseEntity<?> addCustomer(Customer customer){
        if(!(customer.getEmail() == null)) {
            if(customerRepository.existsByEmail(customer.getEmail()).equals(Boolean.FALSE)) {
                customerRepository.save(customer);
                return ResponseEntity.ok().body("The customer is now added");
            }else{
                return ResponseEntity.badRequest().body("Error, this customer already exists");
            }
        }
        return ResponseEntity.badRequest().body("Please fill in the email.");
    }
    public Customer getCustomerById(Long customerId){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            return possibleCustomer.get();
        }
        return null;
    }
    public ResponseEntity<?> updateCustomerById(Long customerId, Customer customer){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            possibleCustomer.get().setFirstName(customer.getFirstName());
            possibleCustomer.get().setLastName(customer.getLastName());
            possibleCustomer.get().setAddress_street(customer.getAddress_street());
            possibleCustomer.get().setAddress_city(customer.getAddress_city());
            possibleCustomer.get().setAddress_country(customer.getAddress_country());
            possibleCustomer.get().setAddress_zipCode(customer.getAddress_zipCode());
            possibleCustomer.get().setEmail(customer.getEmail());
            possibleCustomer.get().setPhoneNumber(customer.getPhoneNumber());
            customerRepository.save(possibleCustomer.get());
            return ResponseEntity.ok().body("The customer is successfully updated.");
        }
        return ResponseEntity.badRequest().body("Error, this customer does not exist.");

    }

    public ResponseEntity<?> deleteCustomerById(Long customerId) {
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            customerRepository.deleteById(customerId);
            return ResponseEntity.ok().body("The customer is deleted successfully.");
        }
        return ResponseEntity.badRequest().body("Please check the customer id again.");


    }
}
