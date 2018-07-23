package com.ctsi.springboot.security.authentication;

import java.io.IOException;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ctsi.springboot.security.service.SsdcUserDetailsService;
import com.ctsi.springboot.security.util.JacksonUtil;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 * 
 * 自定义 AuthenticationProvider 的实现
 *
 */
@Component
public class SsdcAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger log = Logger.getLogger(SsdcAuthenticationProvider.class);
	
	@Autowired
	private SsdcUserDetailsService ssdcUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		log.info("ssdc #### authenticate ");
		try {
			log.info("#### authenticate " + JacksonUtil.bean2Json(authentication));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * 认证的实现
		 */
		SsdcAuthenticationToken auth = (SsdcAuthenticationToken) authentication;
		SsdcUser user = auth.getUser();
		
//		// 通过认证的账号
//		if ("a".equals(user.getUsername()) && "b".equals(user.getPasswd())) {
//			// 认证成功后的 Authentication 对象
//			return new SsdcAuthenticationToken(auth.getUser(), Collections.emptyList());
//		}
//		// 不通过
//		else {
//			
//		}
		
		UserDetails ud = ssdcUserDetailsService.loadUserByUser(user);
//		try {
//			log.info("#### authenticate " + JacksonUtil.bean2Json(ud));
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		if (null != ud) {
			return new SsdcAuthenticationToken(auth.getUser(), Collections.emptyList());
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		log.info("ssdc #### supports " + authentication);
//		return false;
		return authentication.equals(SsdcAuthenticationToken.class);
	}

}
