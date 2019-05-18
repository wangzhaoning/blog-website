package com.blog.init.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.init.domain.Blog;
import com.blog.init.domain.Catalog;
import com.blog.init.domain.Comment;
import com.blog.init.domain.EsBlog;
import com.blog.init.domain.User;
import com.blog.init.domain.Vote;
import com.blog.init.repository.BlogRepository;


/**
 * Blog 服务.
 * 
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private EsBlogService esBlogService;
	
	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		boolean isNew = (blog.getId() == null);
		EsBlog esBlog = null;
		
		Blog returnBlog = blogRepository.save(blog);
		
		if (isNew) {
			esBlog = new EsBlog(returnBlog);
		} else {
			esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
			esBlog.update(returnBlog);
		}
		
		esBlogService.updateEsBlog(esBlog);
		return returnBlog;
	}

	@Transactional
	@Override
	public void removeBlog(Integer id) {
		blogRepository.deleteById(id);
		EsBlog esblog = esBlogService.getEsBlogByBlogId(id);
		esBlogService.removeEsBlog(esblog.getId());
	}

	@Override
	public Optional<Blog> getBlogById(Integer id) {
		return blogRepository.findById(id);
	}

	@Override
	public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		String tags = title;
		Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title, 
				user, tags, user, pageable);
		return blogs;
	}

	@Override
	public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
		return blogs;
	}

	@Override
	public void readingIncrease(Integer id) {
		Optional<Blog> blog = blogRepository.findById(id);
		Blog blogNew = null;

		if (blog.isPresent()) {
			blogNew = blog.get();
			blogNew.setReadSize(blogNew.getReadSize() + 1); // 在原有的阅读量基�?上�?�增1
			this.saveBlog(blogNew);
		}
	}
	
	@Override
	public Blog createComment(Integer blogId, String commentContent) {
		Optional<Blog> optionalBlog = blogRepository.findById(blogId);
		Blog originalBlog = null;
		if(optionalBlog.isPresent()) {
			originalBlog = optionalBlog.get();
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
			Comment comment = new Comment(user, commentContent);
			originalBlog.addComment(comment);
		}

		return this.saveBlog(originalBlog);
	}

	@Override
	public void removeComment(Integer blogId, Integer commentId) {
		Optional<Blog> optionalBlog = blogRepository.findById(blogId);
		if(optionalBlog.isPresent()) {
			Blog originalBlog = optionalBlog.get();
			originalBlog.removeComment(commentId);
			this.saveBlog(originalBlog);
		}
	}
	
	@Override
	public Blog createVote(Integer blogId) {
		Optional<Blog> optionalBlog = blogRepository.findById(blogId);
		Blog originalBlog = null;
 
		if (optionalBlog.isPresent()) {
			originalBlog = optionalBlog.get();
			
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
			Vote vote = new Vote(user);
			boolean isExist = originalBlog.addVote(vote);
			if (isExist) {
				throw new IllegalArgumentException("该用户已经点过赞");
			}
		}

		return this.saveBlog(originalBlog);
	}

	@Override
	public void removeVote(Integer blogId, Integer voteId) {
		Optional<Blog> optionalBlog = blogRepository.findById(blogId);
		Blog originalBlog = null;
 
		if (optionalBlog.isPresent()) {
			originalBlog = optionalBlog.get();
			originalBlog.removeVote(voteId);
			this.saveBlog(originalBlog);
		}
	}
	
	@Override
	public Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable) {
		Page<Blog> blogs = blogRepository.findByCatalog(catalog, pageable);
		return blogs;
	}
}
