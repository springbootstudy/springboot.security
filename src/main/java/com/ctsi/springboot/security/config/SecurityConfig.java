package com.ctsi.springboot.security.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ctsi.springboot.security.authentication.SsdcAuthenticationFilter;
import com.ctsi.springboot.security.authentication.SsdcAuthenticationProvider;
import com.ctsi.springboot.security.authentication.SsdcAuthenticationTokenFilter;

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
	
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private SsdcLoginUrlAuthenticationEntryPoint ssdcLoginUrlAuthenticationEntryPoint;
	
	@Autowired
	private SsdcAuthenticationProvider ssdcAuthenticationProvider;
	
//	@Autowired
//	private LoginAuthenticationProvider loginauthenticationProvider;
	
//	@Autowired
//	private MyAccessDecisionManager accessDecisionManager;
	
//	@Autowired
//	private SsdcAuthenticationSuccessHandler ssdcAuthenticationSuccessHandler;
//	@Autowired
//	private SsdcAuthenticationFailureHandler ssdcAuthenticationFailureHandler;
	
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
		auth.authenticationProvider(ssdcAuthenticationProvider);
//		auth.authenticationProvider(loginauthenticationProvider);
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
		
//		http.authorizeRequests()
//				.accessDecisionManager(accessDecisionManager);
		
		/*
		 * 默认情况下验证要跳转到登录页面，这里设置为自定义的方式
		 */
		http.exceptionHandling().authenticationEntryPoint(ssdcLoginUrlAuthenticationEntryPoint);
		
		http
		/*
		 * 这里不能使用 http.formLogin() 这个进行设置
		 * 此设置是 Spring Security 的 UsernamePassword 默认使用方式，参数名(username, password)，请求方式(post 必须是表单提交)
		 * 下面的过滤器 JwtLoginFilter, JwtAuthenticationFilter 都是针对 UsernamePassword 这种方式的；
		 */
//		 .formLogin()  //  定义当需要用户登录时候，转到的登录页面
				//.loginPage("/login.html")  // 设置登录页面
//				.loginProcessingUrl("/logine")  // 自定义的登录接口
//				.successHandler(ssdcAuthenticationSuccessHandler)
//				.failureHandler(ssdcAuthenticationFailureHandler)
//				.usernameParameter("u")
//				.passwordParameter("p")
//				.and()
				
				.authorizeRequests()  // 定义哪些URL需要被保护、哪些不需要被保护
//				.antMatchers("/login.html", "/login").permitAll()  // 设置所有人都可以访问登录页面和登录接口
				.antMatchers("/login", "/logine").permitAll()
				.antMatchers(HttpMethod.POST, "/hello").permitAll()  // csrf 禁用才有效果
				.antMatchers("/index").hasRole("admin")
				
				.anyRequest().authenticated();  // 任何请求,登录后可以访问
				
//				.and()
//				http.addFilter(new JwtLoginFilter(authenticationManager()));  //验证登陆  
//				.addFilter(new JwtAuthenticationFilter(authenticationManager()));  //验证token
		
//		http.addFilter(ssdcAuthenticationFilter);
		http.addFilterBefore(ssdcAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(new SsdcAuthenticationTokenFilter(authenticationManager()), SsdcAuthenticationFilter.class);
		
		/*
		 * 关闭，可以访问 post 请求
		 * 默认情况下启动跨域
		 * 默认情况下只允许 get 类型请求，限制了除了 get 以外的大多数方法
		 */
		http.csrf().disable(); 
				
	}
	
	// 配置封装 SsdcAuthenticationFilter 的过滤器
	protected SsdcAuthenticationFilter ssdcAuthenticationFilter(	AuthenticationManager authenticationManager) {
		SsdcAuthenticationFilter ssdcAuthenticationFilter = new SsdcAuthenticationFilter();
		// 为过滤器添加认证器
		ssdcAuthenticationFilter.setAuthenticationManager(authenticationManager);
		// 重写认证失败时的跳转页面
//		ssdcAuthenticationFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/ipLogin?error"));
		return ssdcAuthenticationFilter;
	}
	
//	@Bean
//	public AuthenticationManager getAuthenticationManager() throws Exception {
//		logger.info("@@ getAuthenticationManager 注入对象 " + authenticationManager());
//		return authenticationManager();
//	}

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
