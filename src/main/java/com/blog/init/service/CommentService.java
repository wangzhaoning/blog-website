package com.blog.init.service;

import java.util.Optional;

import com.blog.init.domain.Comment;


/**
 * Comment Service接口.
 * 
 */
public interface CommentService {

	/**
     * 根据id获取 Comment
     * @param id
     * @return
     */
	Optional<Comment> getCommentById(Integer id);
    /**
     * 删除评论
     * @param id
     * @return
     */
    void removeComment(Integer id);
}
