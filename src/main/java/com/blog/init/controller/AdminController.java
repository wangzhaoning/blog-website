package com.blog.init.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.init.vo.Menu;

/**
 * ����Ա���ܿ�����
 * @author OJ's big hole
 *
 */
@Controller
@RequestMapping("/admins")
public class AdminController {

	/**
	 * ����Ա���ܵ���
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView listUsers(Model model){
		List<Menu> list = new ArrayList<>();
		list.add(new Menu("�û�����", "/users"));
		model.addAttribute("list", list);
		return new ModelAndView("admins/index", "model", model);
	}

}
