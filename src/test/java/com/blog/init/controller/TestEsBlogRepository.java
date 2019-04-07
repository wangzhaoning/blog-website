package com.blog.init.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.init.domain.EsBlog;
import com.blog.init.repository.EsBlogRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEsBlogRepository{
	@Autowired
	private EsBlogRepository esBlogRepository;
	@Before
	public void init() {
		esBlogRepository.deleteAll();
		esBlogRepository.save(new EsBlog("wangzn","an excellent progreammer","he is graduate CUMT"));
		esBlogRepository.save(new EsBlog("znwang","a good guy","he is want to become a musician"));
	}
	@Test
	public  void testfindDistinctByAll() {
		
		Pageable pageable =PageRequest.of(0, 20);
		String summery="progreammer";
		System.out.println(esBlogRepository.findAll(pageable));
	}
}
