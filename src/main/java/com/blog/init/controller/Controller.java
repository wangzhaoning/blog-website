package com.blog.init.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ������
 */
@RestController
public class Controller {
	@RequestMapping("/hello")
	public String test(){
		return "hello world";
	}
}
