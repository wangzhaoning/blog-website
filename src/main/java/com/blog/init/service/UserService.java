package com.blog.init.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.init.domain.User;


/**
 * �û�����ӿ�
 * @author wangzn
 */
public interface UserService {
	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	User saveOrUpdateUser(User user);
	
	/**
	 * ɾ���û�
	 * @param user
	 * @return
	 */
	void removeUser(Integer id);
	
	/**
	 * ����id��ȡ�û�
	 * @param user
	 * @return
	 */
	Optional<User> getUserById(Integer id);
	
	/**
	 * �����û������з�ҳģ����ѯ
	 * @param user
	 * @return
	 */
	Page<User> listUsersByNameLike(String name, Pageable pageable);

	/**
	 * �����û������в�ѯ
	 * @param user
	 * @return
	 */
	List<User> listUsersByUsernames(Collection<String> usernames);
	
	/**
	 * �����û��������û�
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	User findByEmail(String email);
}
