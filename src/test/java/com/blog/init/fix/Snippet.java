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
 * @date 2019Äê4ÔÂ26ÈÕ  
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Snippet {
	
	@Autowired
	UserService userService;
	
	@Test
	public void test89() {
		try {
			userService.removeUser(6);
		}catch(Exception e) {
			System.out.println("error!!!");
		}
    }

}
