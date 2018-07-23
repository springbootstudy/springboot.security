package com.ctsi.springboot.security.authentication;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author lb
 *
 * @since 2018年7月20日
 * 
 * 自定义认证，模拟 UsernamePasswordAuthenticationToken
 *
 */
public class SsdcAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3168600788812578634L;
	
	private static final Logger log = Logger.getLogger(SsdcAuthenticationToken.class);
	
//	private String eUsername;
//	private String ePassword;
	
	private SsdcUser user;

//	public SsdcAuthenticationToken(
//			Collection<? extends GrantedAuthority> authorities) {
//		super(authorities);
//	}
	
	public SsdcAuthenticationToken(SsdcUser user) {
		super(null);
//		log.info("ssdc #### " + user.getUsername() + ", " + user.getPasswd());
//		this.eUsername = eUsername;
//		this.ePassword = ePassword;
		this.user = user;
		super.setAuthenticated(false); // 注意这个构造方法是认证时使用的
	}
	
	public SsdcAuthenticationToken(SsdcUser user,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
//		log.info("ssdc #### 2 " + user.getUsername() + ", " + user.getPasswd());
		// this.eUsername = eUsername;
		// this.ePassword = ePassword;
		this.user = user;
		super.setAuthenticated(true); // 注意这个构造方法是认证成功后使用的
	}

	@Override
	public Object getCredentials() {
//		log.info("ssdc #### getCredentials");
		return null;
	}

	@Override
	public Object getPrincipal() {
//		log.info("ssdc #### getPrincipal");
		return this.user;
	}

	public SsdcUser getUser() {
		return user;
	}

	public void setUser(SsdcUser user) {
		this.user = user;
	}

//	public String geteUsername() {
//		return eUsername;
//	}
//
//	public void seteUsername(String eUsername) {
//		this.eUsername = eUsername;
//	}
//
//	public String getePassword() {
//		return ePassword;
//	}
//
//	public void setePassword(String ePassword) {
//		this.ePassword = ePassword;
//	}

}
