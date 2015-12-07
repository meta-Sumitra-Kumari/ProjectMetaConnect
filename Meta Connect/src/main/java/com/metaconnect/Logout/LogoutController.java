package com.metaconnect.Logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.metaconnect.user.model.User;


/**
 * @author MetaConnect
 *
 */
@Controller
public class LogoutController {

	/**
	 * @param user --user who is logged in 
	 * @param request  --HttpServletRequest object to close the session
	 * 
	 * @return  will return to index page.
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute("user") User user,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("index");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return modelAndView;
	}
}
