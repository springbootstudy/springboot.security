package com.ctsi.springboot.security.config;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 
 * @author lb
 *
 * @since 2018年7月19日
 *
 * 登录验证失败的处理
 *
 */
//@Component
public class SsdcAuthenticationFailureHandler implements
		AuthenticationFailureHandler {
	
	private static final Logger log = Logger.getLogger(SsdcAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		log.info(">>>> onAuthenticationFailure ");
		
		response.setContentType("application/json;charset=UTF-8");
		// 登录失败给出提示
		try (Writer writer = response.getWriter()) {
			writer.write("faile  " + exception);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
