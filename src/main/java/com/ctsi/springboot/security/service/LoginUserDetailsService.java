package com.ctsi.springboot.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 *
 */
public interface LoginUserDetailsService {
	
	/**
	 * 根据用户名密码验证用户信息
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameNotFoundException
	 */
	Optional<UserDetails> loadUserByUsername(String username, String password) throws UsernameNotFoundException;

}
