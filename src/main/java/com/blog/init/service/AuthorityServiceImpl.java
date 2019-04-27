package com.blog.init.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.init.domain.Authority;
import com.blog.init.repository.AuthorityRepository;


@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Optional<Authority> getAuthorityById(Integer id) {
		return authorityRepository.findById(id);
	}
}
