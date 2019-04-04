package com.blog.init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.blog.init.domain.User;
import com.blog.init.respository.UserRepository;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 查询所有用户
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model){
		model.addAttribute("userList",userRepository.getUserList());
		model.addAttribute("title","用户管理");
		return new ModelAndView("users/list","userModel",model);
	}
	
	/**
	 * 根据id查询用户
	 * @param model
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Integer id,Model model){
		model.addAttribute("user",userRepository.getUserById(id));
		model.addAttribute("title","查看用户");
		return new ModelAndView("users/view","userModel",model);
	}
	
	/**
	 * 获取创建表单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model){
		model.addAttribute("user",new User());
		model.addAttribute("title","创建用户");
		return new ModelAndView("users/form","userModel",model);
	}
	
	/**
	 * 新增创建表单页面
	 * @param model
	 * @return
	 */
	@PostMapping
	public ModelAndView saveOrUpdateForm(User user){
		userRepository.saveUser(user);
		return new ModelAndView("redirect:/users");
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		userRepository.deleteUserById(id);
		return new ModelAndView("redirect:/users");
	}

	/**
	 * 获取创建表单页面
	 * @param id,model
	 * @return
	 */
	@GetMapping("/modify/{id}")
	public ModelAndView modify(@PathVariable("id") Integer id,Model model){
		User user =userRepository.getUserById(id);
		model.addAttribute("user",user);
		model.addAttribute("title","修改用户");
		return new ModelAndView("users/form","userModel",model);
	}

}
