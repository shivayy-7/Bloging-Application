package com.blogapp_10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp_10.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
         User findByEmail(String email);
}
