package com.blog.init.respository;

import java.util.List;

import com.blog.init.domain.User;

public interface UserRepository {
	public void saveUser(User user);
	public void deleteUserById(Integer id);
	public User getUserById(Integer id);
	public List<User> getUserList();
}
