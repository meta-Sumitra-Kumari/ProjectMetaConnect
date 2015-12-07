package com.metaconnect.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public class LoginFilter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession(false);
		String requestUri = request.getRequestURI();
		
		if (requestUri.equals("/MetaConnect/")
				|| requestUri.equals("/MetaConnect/OAuth2")) {
			return true;
		}
		if (session != null) {
			User user = (User) session.getAttribute("user");
			
			if (user == null) {
				response.sendRedirect("/MetaConnect/");
				return false;
			}
		} else {
			
			response.sendRedirect("/MetaConnect/");
			return false;

		}
		return true;
	}

}