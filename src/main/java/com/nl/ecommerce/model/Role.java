package com.nl.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nl.ecommerce.security_config.ApplicationUserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long role_id;
    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole roleName;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "roles")
    private Set<Customer> Customers;

    public Role() {
    }

    public Role(Long role_id, ApplicationUserRole roleName) {
        this.role_id = role_id;
        this.roleName = roleName;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
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
