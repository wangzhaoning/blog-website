package com.blog.init.service;

import java.util.Optional;

import com.blog.init.domain.Authority;
/**
 * 
 * @author wangzn
 * @date 2019Äê4ÔÂ23ÈÕ
 */
public interface AuthorityService {
	
	public Optional<Authority> getAuthorityById(Integer id);
	
}
