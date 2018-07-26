package com.ctsi.springboot.security.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author lb
 *
 * @since 2018年7月26日
 *
 * 异常截获处理
 *
 */
public class SsdcHandlerExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger log = Logger.getLogger(SsdcHandlerExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		log.info("异常截获处理");
		
		return null;
	}

}
