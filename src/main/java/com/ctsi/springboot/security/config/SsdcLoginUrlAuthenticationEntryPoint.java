package com.ctsi.springboot.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 
 * @author lb
 *
 * @since 2018年7月19日
 *
 * 当用户没有权限访问某个资源的时候，你可以在这里自定义返回内容
 *
 */
public class SsdcLoginUrlAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {
	
	private static final Logger log = Logger.getLogger(SsdcLoginUrlAuthenticationEntryPoint.class);

	public SsdcLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
		log.info("0000 SsdcLoginUrlAuthenticationEntryPoint");
	}
	
	// 当访问的资源没有权限，会调用这里
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		log.info("0000 重写");
		super.commence(request, response, authException);
		
		
	}

}
