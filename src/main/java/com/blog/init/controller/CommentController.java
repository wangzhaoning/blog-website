package com.blog.init.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.init.domain.Blog;
import com.blog.init.domain.Comment;
import com.blog.init.domain.User;
import com.blog.init.service.BlogService;
import com.blog.init.service.CommentService;
import com.blog.init.util.ConstraintViolationExceptionHandler;
import com.blog.init.vo.Response;


/**
 * 评论 控制�?.
 * 
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CommentService commentService;

	/**
	 * 获取评论列表
	 * 
	 * @param blogId
	 * @param model
	 * @return
	 */
	@GetMapping
	public String listComments(@RequestParam(value = "blogId", required = true) Integer blogId, Model model) {
		Optional<Blog> optionalblog = blogService.getBlogById(blogId);
		List<Comment> comments = null;

		if (optionalblog.isPresent()) {
			comments = optionalblog.get().getComments();
		}

		// 判断操作用户是否是评论的�?有�??
		String commentOwner = "";
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() 
				&& !SecurityContextHolder
						.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal != null) {
				commentOwner = principal.getUsername();
			}
		}

		model.addAttribute("commentOwner", commentOwner);
		model.addAttribute("comments", comments);
		return "/userspace/blog :: #mainContainerRepleace";
	}

	/**
	 * 发表评论
	 * 
	 * @param blogId
	 * @param commentContent
	 * @return
	 */
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')") // 指定角色权限才能操作方法
	public ResponseEntity<Response> createComment(Integer blogId, String commentContent) {

		try {
			blogService.createComment(blogId, commentContent);
		} catch (ConstraintViolationException e) {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}

		return ResponseEntity.ok().body(new Response(true, "处理成功", null));
	}

	/**
	 * 删除评论
	 * 
	 * @return
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')") // 指定角色权限才能操作方法
	public ResponseEntity<Response> delete(@PathVariable("id") Integer id, Integer blogId) {

		boolean isOwner = false;
		Optional<Comment> optionalComment = commentService.getCommentById(id);
		User user = null;

		if (optionalComment.isPresent()) {
			user = optionalComment.get().getUser();
		} else {
			return ResponseEntity.ok().body(new Response(false, "不存在该评论"));
		}

		// 判断操作用户是否是评论的�?有�??
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() 
				&& !SecurityContextHolder
						.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal != null && user.getUsername().equals(principal.getUsername())) {
				isOwner = true;
			}
		}

		if (!isOwner) {
			return ResponseEntity.ok().body(new Response(false, "没有操作权限"));
		}

		try {
			blogService.removeComment(blogId, id);
			commentService.removeComment(id);
		} catch (ConstraintViolationException e) {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}

		return ResponseEntity.ok().body(new Response(true, "处理成功", null));
	}
}
