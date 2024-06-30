package com.alwyn.techie.stockmanagement.repository;

import com.alwyn.techie.stockmanagement.enums.ERole;
import com.alwyn.techie.stockmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
