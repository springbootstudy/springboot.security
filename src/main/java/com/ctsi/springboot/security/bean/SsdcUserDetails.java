package com.ctsi.springboot.security.bean;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.ctsi.springboot.security.authentication.SsdcUser;

/**
 * 
 * @author lb
 *
 * @since 2018年7月23日
 *
 */
public class SsdcUserDetails extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6308170881289910806L;
	
	public SsdcUserDetails(SsdcUser ssdcUser) {
		super(ssdcUser.getUsername(), ssdcUser.getPasswd(), new ArrayList<GrantedAuthority>());
	}
	
	public SsdcUserDetails(SsdcUser ssdcUser,
			Collection<? extends GrantedAuthority> authorities) {
		super(ssdcUser.getUsername(), ssdcUser.getPasswd(), authorities);
	}

	public SsdcUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public SsdcUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

}
