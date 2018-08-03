package com.ctsi.springboot.security.authentication;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.ctsi.springboot.security.util.JacksonUtil;

/**
 * 
 * @author lb
 *
 * @since 2018年7月31日
 *
 * 权限验证
 *
 */
public class SsdcAccessDecisionVoter implements AccessDecisionVoter<Object> {
	
	private static final Logger log = Logger.getLogger(SsdcAccessDecisionVoter.class);
	
	private final Map<String, String> map = new HashMap<String, String>();
	
	public SsdcAccessDecisionVoter() {
		map.put("/index", "user");
		map.put("/hello", "user");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		log.info("权限验证 supports ConfigAttribute " + attribute);
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("权限验证 supports Class " + clazz);
		return true;
	}

	/*
	 * 如何加入动态权限
	 * vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) 
	 * 里的Object object的类型是FilterInvocation，
	 * 可以通过getRequestUrl获取当前请求的URL
	 * FilterInvocation fi = (FilterInvocation) object;
  	 * String url = fi.getRequestUrl();
  	 * 
  	 * 因此这里扩展空间就大了，可以从DB动态加载，然后判断URL的ConfigAttribute就可以了
	 */
	@Override
	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		log.info("权限验证 vote ");
		
		int vote = ACCESS_DENIED;
		
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		log.info("" + fi);
		
		try {
//			log.info(JacksonUtil.bean2Json(authentication));
//			log.info(JacksonUtil.bean2Json(fi));
			log.info(JacksonUtil.bean2Json(attributes));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		String role = map.get(url);
		if (null == role) {
			vote = ACCESS_DENIED;
		}
		
		Collection<? extends GrantedAuthority> authority = authentication.getAuthorities();
		for (GrantedAuthority a : authority) {
			if (role.equals(a.getAuthority())) {
				return ACCESS_GRANTED;
			}
		}
		
		return vote;
	}

}
