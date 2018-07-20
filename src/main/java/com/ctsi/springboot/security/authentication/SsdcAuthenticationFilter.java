package com.ctsi.springboot.security.authentication;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 
 * @author lb
 *
 * @since 2018年7月20日
 *
 *
 */
public class SsdcAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {
	
	private static final Logger log = Logger.getLogger(SsdcAuthenticationFilter.class);

	public SsdcAuthenticationFilter() {
		// GET 请求方式
		super(new AntPathRequestMatcher("/logine"));
		log.info("ssdc #### ");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name + "#" + request.getParameter(name));
		}
		
		String username = request.getParameter("u");
		String passwd     = request.getParameter("p");
		log.info("ssdc attemptAuthentication #### " + username + ", " + passwd);
		
		if (username == null) {
			username = "";
		}
		username = username.trim();
		if (passwd == null) {
			passwd = "";
		}
		
		SsdcUser user = new SsdcUser();
		user.setUsername(username);
		user.setPasswd(passwd);
		
		SsdcAuthenticationToken ssdcAuthenticationToken = new SsdcAuthenticationToken(user);
		
		return getAuthenticationManager().authenticate(ssdcAuthenticationToken);
	}

}
