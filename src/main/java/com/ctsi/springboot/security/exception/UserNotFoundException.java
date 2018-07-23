package com.ctsi.springboot.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author lb
 *
 * @since 2018年7月23日
 *
 */
public class UserNotFoundException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5360904670584385921L;

	public UserNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}

}
