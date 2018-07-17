package com.ctsi.springboot.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctsi.springboot.security.bean.LoginUserDetails;
import com.ctsi.springboot.security.service.LoginUserDetailsService;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 *
 */
@Service
public class LoginUserDetailsServiceImpl implements LoginUserDetailsService {
	
	private static final Logger log = Logger.getLogger(LoginUserDetailsServiceImpl.class);

	@Override
	public Optional<UserDetails> loadUserByUsername(String username, String password)
			throws UsernameNotFoundException {
		log.info("### 加载用户信息");
		if (!validate(username, password)) {
			return Optional.empty();
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		LoginUserDetails user = new LoginUserDetails(username, password, authorities);
		
		return Optional.ofNullable(user);
	}
	
	private boolean validate(String username, String password) {
		log.info("### 查用户名和密码");
		/*
		 * 从数据库验证
		 */
		if ("a".equals(username) && "b".equals(password)) {
			return true;
		}
		
		return false;
	}

}
