package com.ctsi.springboot.security.config;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * @author lb
 *
 * @since 2018年7月19日
 *
 * 登录验证成功的处理
 *
 */
@Component
public class SsdcAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	
	private static final Logger log = Logger.getLogger(SsdcAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("<<<< onAuthenticationSuccess ");
		
		response.setContentType("application/json;charset=UTF-8");
		// 登录成功返回 token 
		try ( Writer writer = response.getWriter() ) {
			writer.write("success token " + authentication);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
