package com.nl.ecommerce.security_config;

import com.nl.ecommerce.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private Customer customer;

   public UserDetailsImpl(Customer customer) {
        this.customer = customer;
    }



    @Override //the person that has been found inside the database, what was the roles that he suppose to have?
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        return authorities;
    }


    @Override
    public String getPassword() {

        /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String result = encoder.encode(appUser.getPassword());

        return result;*/

        //what was the password he registered to have inside the database.
        return customer.getPassword();
    }

    @Override//what was the username registered inside the database.
    public String getUsername() {
        return customer.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
