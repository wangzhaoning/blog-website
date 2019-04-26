package com.blog.init.fix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.init.domain.User;
import com.blog.init.repository.UserRepository;
import com.blog.init.service.UserService;

/**
 * @author wangzn
 * @date 2019年4月26日  
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPassword {
	
	@Autowired
	UserService userService;
	
	@Test
	public void test89() {
		
		User user = userService.findByUsername("wzn");
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 加密
		String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
		user.setPassword(encodedPassword);
		
		userService.saveOrUpdateUser(user);
    }

}
