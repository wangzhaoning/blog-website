package com.blog.init.respository;


import org.springframework.data.repository.CrudRepository;

import com.blog.init.domain.User;

public interface UserRepository extends CrudRepository<User,Integer>{
	
}
