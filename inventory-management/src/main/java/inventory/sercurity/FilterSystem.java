package inventory.sercurity;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import inventory.model.Auth;
import inventory.model.UserRole;
import inventory.model.Users;
import inventory.util.Constant;

/* This Filter class will handle user not login yet but go to home page by url/ user has no permission access function but go by url
This class will process before go to controller*/

public class FilterSystem implements HandlerInterceptor{
	Logger log = Logger.getLogger(FilterSystem.class);
	
	@Override
	/* Check user has logined or has permission to access functions
	 *@HttpServletRequest request from user
	 *@HttpServletResponse response from server */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		log.info("Request URI: " + request.getRequestURI());
		// Check in session, if USER_INFO session null=> go to login page /not null, user has logned in => go to home page
		Users user = (Users) request.getSession().getAttribute(Constant.USER_INFO);
		//if not logned in, redirect to login page first
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		// if logned in, check url that user has permission to access => if not => go to access denied page
		if(user != null) {
			String url = request.getServletPath(); // get path request ex: /category/add
			if (!hasPermission(url, user)) {
				response.sendRedirect(request.getContextPath() + "/access-denied"); // go to access denied
				return false;
			}
		}
		// user can access
		return true;
	}
	
	/*Check user has permission to access that url or not
	 *@String url get url user request
	 *@User get user info*/
	private boolean hasPermission(String url, Users users) {
		if (url.contains("/index") || url.contains("/access-denied") || url.contains("/logout")) {
			return true;
		}
		//Get user login role
		UserRole userRole = (UserRole) users.getUserRoles().iterator().next();
		// Check that role is authorized to access in any functions
		Set<Auth> auths = userRole.getRole().getAuths();
		// Check each auth in list auths if there any url can access match with url user request
		for (Object obj : auths) {
			Auth auth = (Auth) obj;
			// if match => check Permission is 1 or not
			if (url.contains(auth.getMenu().getUrl())) {
				// if permission == 1 => return true
				// if permission != 1 => return false
				return auth.getPermission() == 1;
			}
		}
		// not any url can access match wiht url user reuqest in menu
		return false;
	}
}
