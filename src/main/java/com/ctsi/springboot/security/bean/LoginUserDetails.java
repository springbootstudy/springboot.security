package com.ctsi.springboot.security.bean;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 *
 */
public class LoginUserDetails extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3908577999914794203L;

	public LoginUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
	
	public LoginUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public LoginUserDetails(String username, String password) {
		super(username, password, new ArrayList<GrantedAuthority>());
	}
		
}
