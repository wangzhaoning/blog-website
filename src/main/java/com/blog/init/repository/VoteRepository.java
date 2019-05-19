package com.blog.init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.init.domain.Vote;


/**
 * Vote Repository接口.
 */
public interface VoteRepository extends JpaRepository<Vote, Integer> {
	
}
