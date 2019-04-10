package com.blog.init.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	/**
	 * ��ȫ������
	 * @author 10786
	 *
	 */
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * �Զ�������
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()//��Щ·�������Է���  
			.antMatchers("/users/**").hasRole("ADMIN")//��Ҫ��Ӧ�Ľ�ɫ���ܷ��� 
			.and()
			.formLogin()//����form����¼��֤  
			.loginPage("/login").failureForwardUrl("/login-error");//�Զ����¼����
	}
	
	/**
	 * ��֤��Ϣ����
	 * @param auth
	 * @throws Exception
	 */
	@Autowired  
	public void configureGloble(AuthenticationManagerBuilder auth) throws Exception{  
	        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("wzn").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
	} 
	

}
