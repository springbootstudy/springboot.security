package com.ctsi.springboot.security.config;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ctsi.springboot.security.bean.AjaxData;
import com.ctsi.springboot.security.util.JacksonUtil;

/**
 * 
 * @author lb
 *
 * @since 2018年7月19日
 *
 * 当用户没有权限访问某个资源的时候，你可以在这里自定义返回内容
 * 默认情况下登陆失败会跳转页面，这里可以进行自定义：
 * 返回 json 字符串
 * 或者是其他(前后端分离里，不要跳转页面，因为没有)
 *
 */
@Component
public class SsdcLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private static final Logger log = Logger.getLogger(SsdcLoginUrlAuthenticationEntryPoint.class);
	
	// 当访问的资源没有权限，会调用这里
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
//		log.info("0000 重写");
		response.setContentType("application/json;charset=UTF-8");
		
		log.info("0000 token为空");
		AjaxData ajaxData = null;
		try ( Writer writer = response.getWriter() ) {
			ajaxData = new AjaxData(1000, "请登录系统");
			writer.write(JacksonUtil.bean2Json(ajaxData));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
//		this.isAjaxRequest(request);
	}
	
	/**
	 * 判断是否 Ajax 请求
	 * @param request
	 * @return
	 */
	public boolean isAjaxRequest(HttpServletRequest request) {
		
		// 输出所有的 header 头
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
//			log.info("0000 " + names.nextElement());
			String header = names.nextElement();
			System.out.println(header + "##" + request.getHeader(header));
		}
		
        String ajaxFlag = request.getHeader("X-Requested-With");
        
//        String xReq = request.getHeader("x-requested-with");
//        if (StringUtils.isNotBlank(xReq) && "XMLHttpRequest".equalsIgnoreCase(xReq)) {
//            // 是ajax异步请求
//        }
        
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }


}


//public class SsdcLoginUrlAuthenticationEntryPoint extends
//LoginUrlAuthenticationEntryPoint {
//
//private static final Logger log = Logger.getLogger(SsdcLoginUrlAuthenticationEntryPoint.class);
//
//public SsdcLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
//super(loginFormUrl);
//log.info("0000 SsdcLoginUrlAuthenticationEntryPoint");
//}
//
//// 当访问的资源没有权限，会调用这里
//@Override
//public void commence(HttpServletRequest request,
//	HttpServletResponse response, AuthenticationException authException)
//	throws IOException, ServletException {
//log.info("0000 重写");
//super.commence(request, response, authException);
//
//
//}
//
//}
