package com.ctsi.springboot.security.authentication;

/**
 * 
 * @author lb
 *
 * @since 2018年7月20日
 *
 */
public class SsdcUser {
	
	private String username;
	private String passwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
