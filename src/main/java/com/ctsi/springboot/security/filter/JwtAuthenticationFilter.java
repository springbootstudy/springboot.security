package com.ctsi.springboot.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 
 * @author lb
 *
 * @since 2018年7月18日
 *
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	
	private static final Logger log = Logger.getLogger(JwtAuthenticationFilter.class);
	
//	public JwtAuthenticationFilter() {
//		super(authenticationManager);
//		log.info("++++ JwtAuthenticationFilter");
//	}

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		log.info("++++ JwtAuthenticationFilter " + authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("++++ doFilterInternal");
		super.doFilterInternal(request, response, chain);
	}

}
