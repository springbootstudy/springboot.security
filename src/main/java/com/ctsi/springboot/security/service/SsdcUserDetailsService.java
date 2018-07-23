package com.ctsi.springboot.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ctsi.springboot.security.authentication.SsdcUser;
import com.ctsi.springboot.security.exception.UserNotFoundException;

/**
 * 
 * @author lb
 *
 * @since 2018年7月23日
 *
 */
public interface SsdcUserDetailsService {
	
	/**
	 * 根据用户信息验证，通过则返回详情，否则返回 null
	 * @param usernamem
	 * @param password
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserByUser(SsdcUser user) throws UserNotFoundException;

}
