package com.ctsi.springboot.security.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ctsi.springboot.security.service.LoginUserDetailsService;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 *
 */
//@Component
public class LoginAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {
	
	private static final Logger log = Logger.getLogger(LoginAuthenticationProvider.class);
	
	private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		log.info("$$$$ additionalAuthenticationChecks");
		
		if (authentication.getCredentials() == null) {
            log.info("$$$$ Authentication failed: no credentials provided");
            throw new BadCredentialsException("Bad credentials: " + userDetails);
        }
		
		if(!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			log.info("$$$$ Authentication failed: password does not match stored value");
            throw new BadCredentialsException("Bad credentials:" + userDetails);
		}
		
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		log.info("$$$$ retrieveUser ");
		
		String password = authentication.getCredentials().toString();
		log.info("$$$$ " + username + ", " + password);
		
		UserDetails loadedUser = null;
		try {
			loadedUser = loginUserDetailsService.loadUserByUsername(username, password);
		}
		catch (UsernameNotFoundException u) {
			u.printStackTrace();
			throw u;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationServiceException(e.getMessage(), e);
		}
		
		if (null == loadedUser) {
			throw new AuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
		}
		
		return loadedUser;
	}

}
