package com.blog.init.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.init.domain.Authority;
import com.blog.init.domain.User;
import com.blog.init.service.AuthorityService;
import com.blog.init.service.UserService;
import com.blog.init.util.MyException;

@Controller
public class MainController {
	
	private static final Integer ROLE_USER_AUTHORITY_ID = 2;
		
	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private UserService userService;
	@GetMapping("/")
	public String root(){
		return "redirect:/index";
	}
	
	/**
	 * 主页跳转
	 * @return
	 */
	@GetMapping("/index")
	public String index(){
		return "index";
	}
	
	/**
	 * 登录跳转
	 * @return
	 */
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	/**
	 * 登录失败跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/login-error")
	public String loginError(Model model){
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "登录失败,用户名或者密码错误");
		return "login";
	}
	
	/**
	 * 注册跳转
	 * @return
	 */
	@GetMapping("/register")
	public String register(){
		return "register";
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 * @throws MyException 
	 */
	@PostMapping("/register")
	public String registerUser(User user) throws MyException {
		
		if(userService.findByUsername(user.getUsername())!=null){
			throw new MyException("*用户名已存在,请更换用户名");
		}
		
		if(userService.findByEmail(user.getEmail())!=null){
			throw new MyException("*邮箱已存在,请更换邮箱");
		}
		
		List<Authority> authorities=new ArrayList<Authority>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);
		
		userService.saveOrUpdateUser(user);
		return "redirect:/login";
	}
	
	
}
