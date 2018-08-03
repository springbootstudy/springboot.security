package com.ctsi.springboot.security.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.ctsi.springboot.security.authentication.SsdcPermissionEvaluator;

/**
 * 
 * @author lb
 *
 * @since 2018年7月31日
 *
 */

//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SsdcGlobalMethodSecurityConfiguration extends
		GlobalMethodSecurityConfiguration {
	
	private static final Logger log = Logger.getLogger(SsdcGlobalMethodSecurityConfiguration.class);
	
	@Autowired
	private SsdcPermissionEvaluator ssdcPermissionEvaluator;
	
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		log.info("全局权限控制 ");
//		return super.createExpressionHandler();
		
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler(); 
		expressionHandler.setPermissionEvaluator(ssdcPermissionEvaluator); 
		return expressionHandler;
	}

}
