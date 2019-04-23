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
	 * ��ҳ��ת
	 * @return
	 */
	@GetMapping("/index")
	public String index(){
		return "index";
	}
	
	/**
	 * ��¼��ת
	 * @return
	 */
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	/**
	 * ��¼ʧ����ת
	 * @param model
	 * @return
	 */
	@GetMapping("/login-error")
	public String loginError(Model model){
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "��¼ʧ��,�û��������������");
		return "login";
	}
	
	/**
	 * ע����ת
	 * @return
	 */
	@GetMapping("/register")
	public String register(){
		return "register";
	}
	
	/**
	 * ע���û�
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 * @throws MyException 
	 */
	@PostMapping("/register")
	public String registerUser(User user) throws MyException {
		
		if(userService.findByUsername(user.getUsername())!=null){
			throw new MyException("*�û����Ѵ���,������û���");
		}
		
		if(userService.findByEmail(user.getEmail())!=null){
			throw new MyException("*�����Ѵ���,���������");
		}
		
		List<Authority> authorities=new ArrayList<Authority>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);
		
		userService.saveOrUpdateUser(user);
		return "redirect:/login";
	}
	
	
}
