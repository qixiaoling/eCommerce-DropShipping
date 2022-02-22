package com.nl.ecommerce.service;

import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.model.Role;
import com.nl.ecommerce.payload.request.SignupRequest;
import com.nl.ecommerce.payload.response.MessageResponse;
import com.nl.ecommerce.repository.CustomerRepository;
import com.nl.ecommerce.repository.RoleRepository;
import com.nl.ecommerce.security_config.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return ResponseEntity.ok().body(customers);
    }
    public ResponseEntity<?> registerCustomer(@Valid SignupRequest signupRequest) {
       if(Boolean.TRUE.equals(customerRepository.existByUsername(signupRequest.getUsername()))){
           return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
       }

       if(Boolean.TRUE.equals(customerRepository.existByEmail(signupRequest.getEmail()))){
           return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
       }

       Customer customer = new Customer(signupRequest.getUsername(),
               signupRequest.getEmail(),
               passwordEncoder().encode(signupRequest.getPassword())
       );

       Set<String> strRoles = signupRequest.getRole();
       Set<Role> roles = new HashSet<>();

       if(strRoles == null) {
           Role userRole = roleRepository.findByName(ApplicationUserRole.USER)
                   .orElseThrow(()-> new RuntimeException());
           roles.add(userRole);
       }else {
           strRoles.forEach(role->{
               switch (role) {
                   case "ADMIN":
                       Role adminRole = roleRepository.findByName(ApplicationUserRole.ADMIN)
                               .orElseThrow(()-> new RuntimeException());
                       roles.add(adminRole);
                   case "mod":
                       Role modRole = roleRepository.findByName(ApplicationUserRole.MOD)
                               .orElseThrow(() -> new RuntimeException());
                       roles.add(modRole);

                       break;
                   default:
                       Role userRole = roleRepository.findByName(ApplicationUserRole.USER)
                               .orElseThrow(() -> new RuntimeException());
                       roles.add(userRole);
               }
           });
       }
       customer.setRoles(roles);
       customerRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

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
