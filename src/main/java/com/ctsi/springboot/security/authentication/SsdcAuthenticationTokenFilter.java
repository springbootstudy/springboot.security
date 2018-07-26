package com.ctsi.springboot.security.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.ctsi.springboot.security.util.JwtUtil;

/**
 * 
 * @author lee
 * 
 * @since 2018年7月21日
 * 
 * 过滤 Token 
 *
 */
public class SsdcAuthenticationTokenFilter extends BasicAuthenticationFilter {
	
	public SsdcAuthenticationTokenFilter(
			AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	private static final Logger log = Logger.getLogger(SsdcAuthenticationTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("非登录请求的过滤");
		String method = request.getMethod();
		String token = request.getHeader("token");
		log.info("ssdc #### " + method + ", " + request.getRequestURI() + ", " + token);
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");  
		
		// 判定是否预检请求
		if ("OPTIONS".equals(request.getMethod())) {
			log.info("## 处理预检");
			response.setStatus(HttpStatus.NO_CONTENT.value());
			//当判定为预检请求后，设定允许请求的头部类型
			response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Token"); 
			//当判定为预检请求后，设定允许请求的方法
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
			// 单位秒
			response.addHeader("Access-Control-Max-Age", "60"); 
			
			return;
		}
		
		if (StringUtils.isEmpty(token)) {
			log.info("ssdc ####  token为空");
//			filterChain.doFilter(request, response);
//			return;
		}
		else {
			try {
				Claims claims = JwtUtil.getClaimsFromToken(token);
				String username = (String) claims.get("username");
				log.info("ssdc #### " + username);
				
				SsdcUser user = new SsdcUser();
				user.setUsername(username);
//				SsdcAuthenticationToken sat = new SsdcAuthenticationToken(user, Collections.emptyList());
				
//				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//				log.info("ssdc #### " + auth);
				
				// 设置 认证通过
				SsdcAuthenticationToken sat = new SsdcAuthenticationToken(user, Collections.emptyList());
//				log.info("ssdc #### " + JacksonUtil.bean2Json(sat));
				SecurityContextHolder.getContext().setAuthentication(sat);
				
//				auth = SecurityContextHolder.getContext().getAuthentication();
//				log.info("ssdc #### " + JacksonUtil.bean2Json(auth));
//				filterChain.doFilter(request, response);
			}
			catch (ExpiredJwtException ex) {
				log.info("ssdc ####  token 过期");
				ex.printStackTrace();
			}
			catch (Exception ex) {
				log.info("ssdc ####  解析token出错");
				ex.printStackTrace();
			}
		}
		
		filterChain.doFilter(request, response);
	}

}

//public class SsdcAuthenticationTokenFilter extends OncePerRequestFilter {
//	
//	private static final Logger log = Logger.getLogger(SsdcAuthenticationTokenFilter.class);
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request,
//			HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		log.info("ssdc #### doFilterInternal ");
//		String method = request.getMethod();
//		log.info("ssdc #### " + method);
//		
//		
//	}
//
//}
