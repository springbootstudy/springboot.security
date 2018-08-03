package com.ctsi.springboot.security.service.impl;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ctsi.springboot.security.authentication.SsdcUser;
import com.ctsi.springboot.security.bean.SsdcUserDetails;
import com.ctsi.springboot.security.exception.UserNotFoundException;
import com.ctsi.springboot.security.service.SsdcUserDetailsService;

/**
 * 
 * @author lb
 *
 * @since 2018年7月23日
 *
 *
 */
@Service
public class SsdcUserDetailsServiceImpl implements SsdcUserDetailsService {
	
	private static final Logger log = Logger.getLogger(SsdcUserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUser(SsdcUser user)
			throws UserNotFoundException {
//		try {
//			log.info("ssdc ####  " + JacksonUtil.bean2Json(user));
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		
		log.info("ssdc ####  检查用户是否合法");
		// 通过认证的账号
		if ("a".equals(user.getUsername()) && "b".equals(user.getPasswd())) {
			// To Do ... 
			log.info("ssdc ####  合法");
			// 认证成功后
			return new SsdcUserDetails(user);
		}
		// 不通过
		else {
			log.info("ssdc ####  不合法");
		}
		
		return null;
	}

}
