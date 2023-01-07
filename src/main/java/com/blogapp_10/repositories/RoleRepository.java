package com.blogapp_10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp_10.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
     Role findByName(String name);
}
