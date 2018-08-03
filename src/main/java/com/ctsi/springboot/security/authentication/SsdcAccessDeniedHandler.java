package com.ctsi.springboot.security.authentication;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.ctsi.springboot.security.bean.AjaxData;
import com.ctsi.springboot.security.util.JacksonUtil;

/**
 * 
 * @author lb
 *
 * @since 2018年7月25日
 *
 * 权限不足的处理
 * 
 * AccessDeniedHandler: 已授权的用户请求权限之外的资源时会交给这个类处理
 * AuthenticationEntryPoint: 未授权的用户请求非公共资源时会交给这个类处理
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
		
		response.setContentType("application/json;charset=UTF-8");
		
		AjaxData ajaxData = null;
		try ( Writer writer = response.getWriter() ) {
			ajaxData = new AjaxData(1000, "权限不足");
			writer.write(JacksonUtil.bean2Json(ajaxData));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
