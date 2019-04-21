package com.blog.init.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author wangzn
 * @date 2019Äê4ÔÂ21ÈÕ
 */
@Data
@AllArgsConstructor
public class Menu implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String url;
	
	public Menu(String name, String url) {
		this.name = name;
		this.url = url;
	}
}
