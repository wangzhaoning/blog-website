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
	
}
