package com.metaconnect.notification.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.metaconnect.notification.service.NotificationService;
import com.metaconnect.user.model.User;
import com.metaconnect.notification.model.Notification;
/**
 * @author MetaConnect
 *
 */
@SessionAttributes("user")
@Controller
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	
	/**
	 * @param user --user who is logged in
	 * @return will return the modelAndView to the page in which the set of notification for user is passed.
	 */
	@RequestMapping("showNotification")
	public ModelAndView showNotification(@ModelAttribute("user") User user){
		String redirectUri="notificationPage";
		ModelAndView model = new ModelAndView();
		Set<Notification> notificationForUser = notificationService.getAllNotification(user);
		model.addObject("notificationList", notificationForUser);
		model.setViewName(redirectUri);
		return model;
	}

}
