package com.blog.init.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


	/**
	 * ��ȫ������
	 * @author 10786
	 *
	 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // ���÷�����ȫ����
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String KEY = "wzn";
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean  
	public PasswordEncoder passwordEncoder() {  
	    return new BCryptPasswordEncoder();   // ʹ�� BCrypt ����
	}  

	@Bean  
	public AuthenticationProvider authenticationProvider() {  
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder); // ����������ܷ�ʽ
	    return authenticationProvider;  
	} 
	
	/**
     * �Զ�������
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // �����Է���
    			.antMatchers("/h2-console/**").permitAll() // �����Է���
    			.antMatchers("/admins/**").hasRole("ADMIN") // ��Ҫ��Ӧ�Ľ�ɫ���ܷ���
    			.and()
    			.formLogin()   //���� Form ����¼��֤
    			.loginPage("/login").failureUrl("/login-error") // �Զ����¼����
    			.and().rememberMe().key(KEY) // ���� remember me
    			.and().exceptionHandling().accessDeniedPage("/403");  // �����쳣���ܾ����ʾ��ض��� 403 ҳ��
    	http.csrf().ignoringAntMatchers("/h2-console/**"); // ���� H2 ����̨�� CSRF ����
    	http.headers().frameOptions().sameOrigin(); // ��������ͬһ��Դ��H2 ����̨������
    }
    
    /**
     * ��֤��Ϣ����
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);
    	auth.authenticationProvider(authenticationProvider());
    }
}
