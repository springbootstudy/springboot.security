package com.ctsi.springboot.security.authentication;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

/**
 * 
 * @author lb
 *
 * @since 2018年8月3日
 *
 * 
 *
 */
public class SsdcFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	
	private static final Logger log = Logger.getLogger(SsdcFilterInvocationSecurityMetadataSource.class);
	
	private final Map<String, String> map = new HashMap<String, String>();
	private FilterInvocationSecurityMetadataSource fisms;
	
	private final AntPathMatcher apm = new AntPathMatcher();
	
	public SsdcFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource fisms) {
		this.fisms = fisms;
		
		map.put("/index", "user");
		map.put("/hello", "user");
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		log.info("权限验证 FilterInvocationSecurityMetadataSource 2 ");
		
		FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        log.info(url);
        
//		try {
//			log.info(JacksonUtil.bean2Json(fi));
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}

        for(Map.Entry<String,String> entry : map.entrySet()){
            if( apm.match(entry.getKey(), url) ){
            	log.info("匹配");
                return SecurityConfig.createList(entry.getValue());
            }
        }

        //  返回代码定义的默认配置
        return fisms.getAttributes(object);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		log.info("权限验证 FilterInvocationSecurityMetadataSource 3 ");
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("权限验证 FilterInvocationSecurityMetadataSource " + clazz);
		log.info(FilterInvocation.class.isAssignableFrom(clazz));
		return true;
	}

}
