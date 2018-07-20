package com.ctsi.springboot.security.authentication;

import java.io.IOException;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

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
		
		SsdcAuthenticationToken auth = (SsdcAuthenticationToken) authentication;
		
		return new SsdcAuthenticationToken(auth.getUser(), Collections.emptyList());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		log.info("ssdc #### supports " + authentication);
//		return false;
		return authentication.equals(SsdcAuthenticationToken.class);
	}

}
