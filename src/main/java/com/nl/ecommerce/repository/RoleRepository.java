package com.nl.ecommerce.repository;

import com.nl.ecommerce.model.Role;
import com.nl.ecommerce.security_config.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

    Role findByRoleName (ApplicationUserRole roleName);

}