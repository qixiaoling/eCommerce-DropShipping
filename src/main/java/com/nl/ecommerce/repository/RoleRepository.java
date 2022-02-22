package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Role;
import com.nl.ecommerce.security_config.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <ApplicationUserRole, Long> {

    Optional<Role> findByName(ApplicationUserRole name);

}