package com.ctsi.springboot.security.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author lee
 * 
 * @since 2018年7月21日
 * 
 * 过滤 Token 
 *
 */
public class SsdcAuthenticationTokenFilter extends BasicAuthenticationFilter {
	
	public SsdcAuthenticationTokenFilter(
			AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	private static final Logger log = Logger.getLogger(SsdcAuthenticationTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("ssdc #### doFilterInternal ");
		String method = request.getMethod();
		log.info("ssdc #### " + method);
		
		
	}

}

//public class SsdcAuthenticationTokenFilter extends OncePerRequestFilter {
//	
//	private static final Logger log = Logger.getLogger(SsdcAuthenticationTokenFilter.class);
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request,
//			HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		log.info("ssdc #### doFilterInternal ");
//		String method = request.getMethod();
//		log.info("ssdc #### " + method);
//		
//		
//	}
//
//}
