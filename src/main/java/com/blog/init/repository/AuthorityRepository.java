package com.blog.init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.init.domain.Authority;


/**
 * 权限仓库
 * @author OJ's big hole
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority,Integer>{

}
