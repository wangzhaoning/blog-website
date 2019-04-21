package com.blog.init.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.init.domain.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	/**
	 * 根据用户姓名分页模糊查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> findByNameLike(String name , Pageable pageable);
	
	/**
	 * 根据用户姓名查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	User findByUsername(String name);
}
