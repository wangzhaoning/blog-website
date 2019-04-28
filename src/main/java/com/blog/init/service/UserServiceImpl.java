/**  
* <p>Title: UserServiceImpl.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* @author wangzn  
* @date 2019年4月21日  
*/  
package com.blog.init.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.init.domain.User;
import com.blog.init.repository.UserRepository;

/**
 * 用户服务实现
 * @author wangzn
 *
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public User saveOrUpdateUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	@Override
	public void removeUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public Page<User> listUsersByNameLike(String name, Pageable pageable) {
		name="%"+name+"%";
		Page<User> users= userRepository.findByNameLike(name, pageable);
		return users;
	}

	@Override
	public List<User> listUsersByUsernames(Collection<String> usernames) {
		return userRepository.findByUsernameIn(usernames);
	}


	/* (non-Javadoc)
	 * @see com.blog.init.service.UserService#findByEmail(java.lang.String)
	 */
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.blog.init.service.UserService#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		return (User) userRepository.findByUsername(username);
	}

}
