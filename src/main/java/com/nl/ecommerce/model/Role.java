package com.nl.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nl.ecommerce.security_config.ApplicationUserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole roleName;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "roles")
    private Set<Customer> Customers;

    public Role() {
    }

    public Role(ApplicationUserRole roleName) {
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(Long role_id) {
        this.id = id;
    }

    public ApplicationUserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(ApplicationUserRole roleName) {
        this.roleName = roleName;
    }

    @JsonBackReference
    public Set<Customer> getCustomers() {
        return Customers;
    }

    public void setCustomers(Set<Customer> customers) {
        Customers = customers;
    }
}
