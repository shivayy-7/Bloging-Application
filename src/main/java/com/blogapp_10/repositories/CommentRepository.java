package com.blogapp_10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp_10.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
