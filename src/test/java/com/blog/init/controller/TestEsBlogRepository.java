package com.blog.init.controller;

import java.util.Iterator;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
//		esBlogRepository.save(new EsBlog("wangzn","an excellent progreammer","he was graduated CUMT"));
//		esBlogRepository.save(new EsBlog("znwang","a good guy","he is want to become a musician"));
//		esBlogRepository.save(new EsBlog("znwang","a good guy","he is want to become a teacher"));
//		esBlogRepository.save(new EsBlog("qxy","a good gril","she is a musician"));
	}
	@Test
	public void testfindDistinctByAll() {
		
		Pageable pageable =PageRequest.of(0, 20);
		Page<EsBlog> esBlogs=esBlogRepository.findAll(pageable);
		System.out.println("--------satrt--------");
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder("musician");
		Iterable<EsBlog> searchResult = esBlogRepository.search(builder);
		Iterator<EsBlog> iterator = searchResult.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
//		for(EsBlog esBlog : esBlogs) {
//			System.out.println(esBlog);
//		}
		System.out.println("--------end--------");
	}
}
