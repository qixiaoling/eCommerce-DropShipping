package com.nl.ecommerce.security_config;
import com.nl.ecommerce.model.Customer;
import com.nl.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customerDB = customerRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found."));
        return new UserDetailsImpl(customerDB);

    }

}
