package com.blog.init.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.init.domain.Comment;
import com.blog.init.repository.CommentRepository;


/**
 * Comment Service接口实现.
 * 
 * @since 1.0.0 2017年6月6日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    
	@Override
	public Optional<Comment> getCommentById(Integer id) {
		return commentRepository.findById(id);
	}

	@Override
	public void removeComment(Integer id) {
		commentRepository.deleteById(id);
	}

}
