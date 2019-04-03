package com.blog.init.respository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.blog.init.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);  
	ConcurrentMap<Integer,User> userMap=new ConcurrentHashMap<>();
	private static AtomicInteger autoId=new AtomicInteger();
	@Override
	public void saveUser(User user) {
		Integer id=user.getId();
		if(id==null) {
			id=autoId.incrementAndGet();
			user.setId(id);
			userMap.put(id, user);
			return;
		}
		logger.info("saveUser fail! user id not is null!");
	}

	@Override
	public void deleteUserById(Integer id) {
		userMap.remove(id);
	}

	@Override
	public User getUserById(Integer id) {
		User user =userMap.get(id);
		return user;
	}

	@Override
	public List<User> getUserList() {
		return new ArrayList<User>(this.userMap.values());
		 
	}
	
/*	public static void main(String[] args) {
		User user=new User("1","2","3");
		User user1=new User("2","2","3");
		UserRepositoryImpl test=new UserRepositoryImpl();
		test.saveUser(user);
		test.saveUser(user1);
		System.out.println(test.getUserList());
	}*/

}
