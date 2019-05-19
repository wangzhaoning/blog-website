package com.blog.init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.init.domain.Comment;


/**
 * Comment Repository 接口. 
 * 
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
