package com.blog.init.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
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
	
}
