package com.ctsi.springboot.security.config;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger log = Logger.getLogger(MyAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		log.info("#### authenticate");
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		log.info("#### " + username + ", " + password);
		return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		log.info("#### supports " + authentication);
//		return false;
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
