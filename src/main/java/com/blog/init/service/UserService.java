package com.blog.init.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.init.domain.User;


/**
 * 用户服务接口
 * @author wangzn
 */
public interface UserService {
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	User saveOrUpdateUser(User user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	void removeUser(Integer id);
	
	/**
	 * 根据id获取用户
	 * @param user
	 * @return
	 */
	Optional<User> getUserById(Integer id);
	
	/**
	 * 根据用户名进行分页模糊查询
	 * @param user
	 * @return
	 */
	Page<User> listUsersByNameLike(String name, Pageable pageable);

	/**
	 * 根据用户名进行查询
	 * @param user
	 * @return
	 */
	List<User> listUsersByUsernames(Collection<String> usernames);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	User findByEmail(String email);
}
