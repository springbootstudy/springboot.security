package com.ctsi.springboot.security.authentication;

import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.ctsi.springboot.security.util.JacksonUtil;

/**
 * 
 * @author lb
 *
 * @since 2018年7月30日
 *
 * 权限评估
 * 
 * 
 *
 */
//@Configuration
public class SsdcPermissionEvaluator implements PermissionEvaluator {
	
	private static final Logger log = Logger.getLogger(SsdcPermissionEvaluator.class);

	/*
	 * 配合 IndexController 中的
	 * @PreAuthorize("hasPermission(null, 'read')")
	 * 调试
	 * 
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		log.info("权限评估 2 ");
		try {
			log.info(JacksonUtil.bean2Json(authentication));
			log.info(JacksonUtil.bean2Json(targetDomainObject));
			log.info(JacksonUtil.bean2Json(permission));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		log.info("权限评估 3 ");
		return false;
	}

}
