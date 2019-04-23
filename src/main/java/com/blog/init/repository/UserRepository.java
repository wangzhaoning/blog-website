package com.blog.init.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

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
	UserDetails findByUsername(String name);
	
	/**
	 * 根据用户姓名查询
	 * @param usernames
	 * @return
	 */
	List<User> findByUsernameIn(Collection<String> usernames);

	/**
	 * 根据邮箱查找用户
	 * @param email
	 * @return
	 */
	User findByEmail(String email);
}
