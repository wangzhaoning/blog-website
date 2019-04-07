package com.blog.init.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.blog.init.domain.EsBlog;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
	Page<EsBlog> findDistinctByAll(String title,String summary,String content, Pageable pageable);
}
