package com.blog.init.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.blog.init.domain.Authority;
import com.blog.init.domain.User;
import com.blog.init.service.AuthorityService;
import com.blog.init.service.UserService;
import com.blog.init.util.ConstraintViolationExceptionHandler;
import com.blog.init.vo.Response;

/**
 * �û�������
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * ��ѯ�����û�
	 * @return
	 */
	@GetMapping
	public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<User> page = userService.listUsersByNameLike(name, pageable);
		List<User> list = page.getContent();	// ��ǰ����ҳ�������б�
		
		model.addAttribute("page", page);
		model.addAttribute("userList", list);
		return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
	}
	
	
	/**
	 * ��ȡ�����û�����
	 * @param user
	 * @return
	 */
	@GetMapping("/add")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User(null, null, null, null, null));
		return new ModelAndView("users/add", "userModel", model);
	}
	
	
	
	
	
	 /**
     * ��������޸��û�
     * @param user
     * @param authorityId
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> saveOrUpateUser(User user, Integer authorityId) {
    	
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(authorityId).get());
		user.setAuthorities(authorities);

        try {
            userService.saveOrUpdateUser(user);
        }  catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }

        return ResponseEntity.ok().body(new Response(true, "����ɹ�", user));
    }
	
	/**
	 * ɾ���û�
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
    @DeleteMapping("/{id}")
	public ResponseEntity<Response> removeUser(@PathVariable("id") Integer id) {
		try {
			userService.removeUser(id);
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "����ɹ�"));
	}
	
	/**
	 * ��ȡ�޸��û��Ľ���
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = "edit/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Integer id, Model model) {
		Optional<User> user = userService.getUserById(id);
		model.addAttribute("user", user.get());
		return new ModelAndView("users/edit", "userModel", model);
	}

}
