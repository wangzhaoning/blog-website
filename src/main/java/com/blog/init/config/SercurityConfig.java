package com.blog.init.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	/**
	 * 安全配置类
	 * @author 10786
	 *
	 */
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 自定义配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()//这些路径都可以访问  
			.antMatchers("/users/**").hasRole("ADMIN")//需要相应的角色才能访问 
			.and()
			.formLogin()//基于form表单登录验证  
			.loginPage("/login").failureForwardUrl("/login-error");//自定义登录界面
	}
	
	/**
	 * 认证信息管理
	 * @param auth
	 * @throws Exception
	 */
	@Autowired  
	public void configureGloble(AuthenticationManagerBuilder auth) throws Exception{  
	        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("wzn").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
	} 
	

}
