package com.ctsi.springboot.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * @author lb
 *
 * @since 2018年7月25日
 *
 * 权限不足的处理
 *
 */
@Component
public class SsdcAccessDeniedHandler implements AccessDeniedHandler {
	
	private static final Logger log = Logger.getLogger(SsdcAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		log.info("权限不足的处理 ");
		

	}

}
