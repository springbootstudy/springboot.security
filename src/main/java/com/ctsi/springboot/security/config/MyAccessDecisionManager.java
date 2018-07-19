package com.ctsi.springboot.security.config;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

/**
 * 
 * @author lb
 *
 * @since 2018年7月19日
 *
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
	
	private static final Logger log = Logger.getLogger(MyAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		log.info("==== decide ");

		FilterInvocation filterInvocation = (FilterInvocation) object;
		String url = filterInvocation.getRequestUrl();
		log.info("==== " + url);

		// 如果没有配置访问权限，则不通过
		if (configAttributes == null) {
			throw new AccessDeniedException("未配置访问权限");
		}

		// 直接放行的资源
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		log.info("==== supports attribute ");
		
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("==== supports clazz ");
		
		return true;
	}

}
