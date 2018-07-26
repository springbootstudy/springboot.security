package com.ctsi.springboot.security.authentication;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ctsi.springboot.security.authentication.SsdcAuthenticationToken.RequestType;
import com.ctsi.springboot.security.bean.AjaxData;
import com.ctsi.springboot.security.util.JacksonUtil;
import com.ctsi.springboot.security.util.JwtUtil;

/**
 * 
 * @author lb
 *
 * @since 2018年7月20日
 *
 *
 */
public class SsdcAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {
	
	private static final Logger log = Logger.getLogger(SsdcAuthenticationFilter.class);

	public SsdcAuthenticationFilter() {
		// 过滤 GET 请求方式
		super(new AntPathRequestMatcher("/logine"));
//		super(new AntPathRequestMatcher("/logine", "POST"));
//		log.info("ssdc #### ");
	}
	
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res,
//			FilterChain chain) throws IOException, ServletException {
//		
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) res;
//		log.info("登录过滤器 覆盖 " + request.getMethod());
//		if ("OPTIONS".equals(request.getMethod())) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		super.doFilter(req, res, chain);
//	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		log.info("登录过滤器");
		
		if ("OPTIONS".equals(request.getMethod())) {
			SsdcAuthenticationToken authentication = new SsdcAuthenticationToken(new SsdcUser());
			authentication.setRequestType(RequestType.OPTIONS);
			return getAuthenticationManager().authenticate(authentication);
		}
		
		System.out.println("请求中的全部参数");
		// 请求中的所有参数
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name + "#" + request.getParameter(name));
		}
		
		String username = request.getParameter("username");
		String passwd     = request.getParameter("passwd");
		log.info("ssdc attemptAuthentication #### " + username + ", " + passwd);
		
		if (username == null) {
			username = "";
		}
		username = username.trim();
		if (passwd == null) {
			passwd = "";
		}
		
		SsdcUser user = new SsdcUser();
		user.setUsername(username);
		user.setPasswd(passwd);
		
		// 认证之前的 Authentication 对象
		Authentication ssdcAuthenticationToken = new SsdcAuthenticationToken(user);
		
		return getAuthenticationManager().authenticate(ssdcAuthenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("登录过滤器 成功的调用");
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Token"); 
//		log.info("ssdc #### " + JacksonUtil.bean2Json(authResult));
		
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
		}
		
		if ("GET".equals(request.getMethod())) {
			SsdcAuthenticationToken sat = (SsdcAuthenticationToken) authResult;
	//		sat.getUser().setPasswd(null);
	//		log.info("ssdc #### " + JacksonUtil.bean2Json(sat));
	//		SecurityContextHolder.getContext().setAuthentication(sat);
			
			Map<String, Object> claims = new HashMap<>();
			claims.put("username", sat.getUser().getUsername());
			String token = JwtUtil.generateToken(claims);
			
			AjaxData ajaxData = new AjaxData(0, "OK");
			ajaxData.setToken(token);
			
			// 这里应该生成 Token 并返回
			try (Writer writer = response.getWriter()) {
//				log.info("ssdc #### " + JacksonUtil.bean2Json(ajaxData));
				writer.write(JacksonUtil.bean2Json(ajaxData));
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
