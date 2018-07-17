package com.ctsi.springboot.security.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 
 * @author lb
 *
 * @since 2018年7月17日
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = Logger.getLogger(SecurityConfig.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Autowired
//	private MyAuthenticationProvider authenticationProvider;
	
	@Autowired
	private LoginAuthenticationProvider authenticationProvider;
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		logger.info("@@ configureGlobal");
//		auth.inMemoryAuthentication()
//				.withUser("a").password("b").roles("USER");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		logger.info("@@ configure ");
//		super.configure(auth);
		
		// start 内存方式
		// 内存中的使用方式：用户名和密码
//		auth.inMemoryAuthentication()
//				.withUser("a").password("b").roles("USER");
		/*
		 * 解决办法
		 * There is no PasswordEncoder mapped for the id "null"
		 */
//		auth.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.withUser("a").password(new BCryptPasswordEncoder().encode("b")).roles("USER");
		// end 内存方式
		
		// start 数据库方式
		// 数据库中的使用方式
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
//
//			@Override
//			public String encode(CharSequence rawPassword) {
//				logger.info("@@ " + rawPassword.toString());
//				return rawPassword.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence rawPassword,
//					String encodedPassword) {
//				logger.info("@@ " + rawPassword + ", " + encodedPassword);
//				return encodedPassword.equals(rawPassword);
//			}
//			
//		});
		// end 数据库方式
		
		// start 自定义方式
		auth.authenticationProvider(authenticationProvider);
		// end 自定义方式
	}

//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		logger.info("@@ authenticationManagerBean ");
//		return super.authenticationManagerBean();
//	}

//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		logger.info("@@ authenticationManager ");
//		return super.authenticationManager();
//	}

//	@Override
//	public UserDetailsService userDetailsServiceBean() throws Exception {
//		logger.info("@@ UserDetailsService userDetailsServiceBean ");
//		return super.userDetailsServiceBean();
//	}

//	@Override
//	protected UserDetailsService userDetailsService() {
//		logger.info("@@ UserDetailsService ");
//		return super.userDetailsService();
//	}

//	@Override
//	public void init(WebSecurity web) throws Exception {
//		logger.info("@@ init ");
////		super.init(web);
//		
////		web.ignoring().antMatchers("*.css", "*.js");
//	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		logger.info("@@ configure WebSecurity ");
//		super.configure(web);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("@@ configure HttpSecurity ");
//		super.configure(http);
		
		http.formLogin()  //  定义当需要用户登录时候，转到的登录页面
//				.loginPage("/login.html")  // 设置登录页面
				.loginProcessingUrl("/login")  // 自定义的登录接口
				.and()
				.authorizeRequests()  // 定义哪些URL需要被保护、哪些不需要被保护
				.antMatchers("/login.html", "/login")
				.permitAll()  // 设置所有人都可以访问登录页面和登录接口
				.anyRequest()  // 任何请求,登录后可以访问
				.authenticated();
	}

//	@Override
//	public void setApplicationContext(ApplicationContext context) {
//		logger.info("@@ setApplicationContext ");
//		super.setApplicationContext(context);
//	}

//	@Override
//	public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
//		logger.info("@@ setTrustResolver ");
//		super.setTrustResolver(trustResolver);
//	}

//	@Override
//	public void setContentNegotationStrategy(
//			ContentNegotiationStrategy contentNegotiationStrategy) {
//		logger.info("@@ setContentNegotationStrategy ");
//		super.setContentNegotationStrategy(contentNegotiationStrategy);
//	}

//	@Override
//	public void setObjectPostProcessor(
//			ObjectPostProcessor<Object> objectPostProcessor) {
//		logger.info("@@ setObjectPostProcessor ");
//		super.setObjectPostProcessor(objectPostProcessor);
//	}

//	@Override
//	public void setAuthenticationConfiguration(
//			AuthenticationConfiguration authenticationConfiguration) {
//		logger.info("@@ setAuthenticationConfiguration ");
//		super.setAuthenticationConfiguration(authenticationConfiguration);
//	}

}
