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
import com.blog.init.repository.UserRepository;

/**
 * �û�������
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ��ѯ�����û�
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model){
		model.addAttribute("userList",userRepository.findAll());
		model.addAttribute("title","User management");
		return new ModelAndView("users/list","userModel",model);
	}
	
	/**
	 * ����id��ѯ�û�
	 * @param model
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Integer id,Model model){
		model.addAttribute("user",userRepository.findById(id).get());
		model.addAttribute("title","view user");
		return new ModelAndView("users/view","userModel",model);
	}
	
	/**
	 * ��ȡ������ҳ��
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model){
		model.addAttribute("user",new User(null,null,null));
		model.addAttribute("title","add users");
		return new ModelAndView("users/form","userModel",model);
	}
	
	/**
	 * ����������ҳ��
	 * @param model
	 * @return
	 */
	@PostMapping
	public ModelAndView saveOrUpdateForm(User user){
		userRepository.save(user);
		return new ModelAndView("redirect:/users");
	}
	
	/**
	 * ɾ���û�
	 * @param id
	 * @return
	 */
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		userRepository.deleteById(id);
		return new ModelAndView("redirect:/users");
	}

	/**
	 * ��ȡ������ҳ��
	 * @param id,model
	 * @return
	 */
	@GetMapping("/modify/{id}")
	public ModelAndView modify(@PathVariable("id") Integer id,Model model){
		User user=userRepository.findById(id).get();
		model.addAttribute("user",user);
		model.addAttribute("title","�޸��û�");
		return new ModelAndView("users/form","userModel",model);
	}

}
