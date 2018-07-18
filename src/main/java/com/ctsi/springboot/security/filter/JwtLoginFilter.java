package com.ctsi.springboot.security.filter;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * @author lb
 *
 * @since 2018年7月18日
 *
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final Logger log = Logger.getLogger(JwtLoginFilter.class);
	
	private AuthenticationManager authenticationManager;
	
//	public JwtLoginFilter() {
//		log.info("%%%% " + authenticationManager);
//	}
	
	public JwtLoginFilter(AuthenticationManager authenticationManager) {
		log.info("%%%% 2 " + this.authenticationManager + ", " + authenticationManager);
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		log.info("%%%% attemptAuthentication");
//		return super.attemptAuthentication(request, response);
		
		// 得到用户登陆信息,并封装到 Authentication 中,供自定义用户组件使用.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("%%%% " + username + ", " + password);

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        username = username.trim();

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);

        //authenticate()接受一个token参数,返回一个完全经过身份验证的对象，包括证书.
        // 这里并没有对用户名密码进行验证,而是使用 AuthenticationProvider 提供的 authenticate 方法返回一个完全经过身份验证的对象，包括证书.
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //UsernamePasswordAuthenticationToken 是 Authentication 的实现类
        return authenticationToken;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("%%%% successfulAuthentication");
//		super.successfulAuthentication(request, response, chain, authResult);
		
		// 这里应该生成 Token 并返回
		try ( Writer writer = response.getWriter() ) {
			writer.write("One token .");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
