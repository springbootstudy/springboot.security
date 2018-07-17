package com.ctsi.springboot.security.service.impl;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctsi.springboot.security.web.IndexController;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private static final Logger log = Logger.getLogger(IndexController.class);

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.info("## " + username);
		
		User user = new User(username, "b", AuthorityUtils.NO_AUTHORITIES);
		log.info("## " + user);
		
		return user;
	}

}
