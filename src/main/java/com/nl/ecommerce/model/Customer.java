package com.nl.ecommerce.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address_street;
    private String address_zipCode;
    private String address_city;
    private String address_country;
    private String email;
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Cart_Items> items = new ArrayList<>();

    public Customer(String firstName, String lastName, String address_street, String address_zipCode,
                    String address_city, String address_country, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address_street = address_street;
        this.address_zipCode = address_zipCode;
        this.address_city = address_city;
        this.address_country = address_country;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_zipCode() {
        return address_zipCode;
    }

    public void setAddress_zipCode(String address_zipCode) {
        this.address_zipCode = address_zipCode;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_country() {
        return address_country;
    }

    public void setAddress_country(String address_country) {
        this.address_country = address_country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Cart_Items> getItems() {
        return items;
    }

    public void setItems(List<Cart_Items> items) {
        this.items = items;
    }
}
