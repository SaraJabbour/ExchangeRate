package com.example.exchangeRate.security.repository;

import com.example.exchangeRate.security.models.ERole;
import com.example.exchangeRate.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
