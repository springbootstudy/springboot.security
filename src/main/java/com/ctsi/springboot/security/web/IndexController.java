package com.ctsi.springboot.security.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctsi.springboot.security.util.JwtUtil;

@RestController
public class IndexController {
	
	private static final Logger log = Logger.getLogger(IndexController.class);
	
//	@Autowired
//	private FilterConfig filterConfig;
	
//	@PreAuthorize("hasRole('user')")
//	@PreAuthorize("hasPermission(null, 'read')")
	@RequestMapping("/index")
	public ResponseEntity<String> index(HttpSession session) {
		log.info("## Index " + session.getId());
		
		return new ResponseEntity<String>("OK-" + session.getId(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public ResponseEntity<String> hello(HttpSession session) {
		log.info("## Hello " + session.getId());
		
		return new ResponseEntity<String>("OK-" + session.getId(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/world", method = RequestMethod.POST)
	public ResponseEntity<String> world(HttpSession session) {
		log.info("## World " + session.getId());
		
		return new ResponseEntity<String>("OK-" + session.getId(), HttpStatus.OK);
	}
	
	@RequestMapping("/welcome")
	public ResponseEntity<String> welcome(HttpSession session) {
		log.info("## Welcome " + session.getId());
		
		return new ResponseEntity<String>("OK-" + session.getId(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody User user) {
		String username = user.getUsername();
		String passwd = user.getPasswd();
		
		log.info("## post login " + username + ", " + passwd);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 通过认证的账号
		if ("a".equals(username) && "b".equals(passwd)) {
			Map<String, Object> claims = new HashMap<>();
			String token = JwtUtil.generateToken(claims);

			map.put("token", token);
		}
		// 不通过
		else {
			map.put("error", HttpStatus.UNAUTHORIZED);
		}
		
		return map;
	}
	
	@RequestMapping(value = "/logine", method = RequestMethod.POST)
	public ResponseEntity<Object> logine(@RequestBody User user) {
		String username = user.getUsername();
		String passwd = user.getPasswd();
		
		log.info("## post logine " + username + ", " + passwd);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 通过认证的账号
		if ("a".equals(username) && "b".equals(passwd)) {
			Map<String, Object> claims = new HashMap<>();
			String token = JwtUtil.generateToken(claims);
			// logger.info("## " + token);

			map.put("token", token);
		}
		// 不通过
		else {
			map.put("error", HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	@RequestMapping("/login")
	public Object login(String username, String passwd) {
		log.info("## get login " + username + ", " + passwd);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 通过认证的账号
		if ("a".equals(username) && "b".equals(passwd)) {
//			logger.info("## token flag " + filterConfig.isFilterToken());
//			if (filterConfig.isFilterToken()) {
//				Map<String, Object> claims = new HashMap<>();
//				String token = JwtUtil.generateToken(claims);
////				logger.info("## " + token);
//				
//				map.put("token", token);
//			}
		}
		// 不通过
		else {
			map.put("error", HttpStatus.UNAUTHORIZED);
		}
		
		return map;
	}
	
	@RequestMapping("/mylogin")
	public Object mylogin(String username, String passwd) {
		log.info("## get mylogin " + username + ", " + passwd);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 通过认证的账号
		if ("a".equals(username) && "b".equals(passwd)) {
//			logger.info("## token flag " + filterConfig.isFilterToken());
//			if (filterConfig.isFilterToken()) {
//				Map<String, Object> claims = new HashMap<>();
//				String token = JwtUtil.generateToken(claims);
////				logger.info("## " + token);
//				
//				map.put("token", token);
//			}
		}
		// 不通过
		else {
			map.put("error", HttpStatus.UNAUTHORIZED);
		}
		
		return map;
	}
	
	@RequestMapping(value = "/mylogin", method = RequestMethod.POST)
	public Object mylogin(@RequestBody User user) {
		String username = user.getUsername();
		String passwd = user.getPasswd();
		log.info("## get login " + username + ", " + passwd);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 通过认证的账号
		if ("a".equals(username) && "b".equals(passwd)) {
//			logger.info("## token flag " + filterConfig.isFilterToken());
//			if (filterConfig.isFilterToken()) {
//				Map<String, Object> claims = new HashMap<>();
//				String token = JwtUtil.generateToken(claims);
////				logger.info("## " + token);
//				
//				map.put("token", token);
//			}
		}
		// 不通过
		else {
			map.put("error", HttpStatus.UNAUTHORIZED);
		}
		
		return map;
	}

}

class User {
	
	private String username;
	private String passwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
