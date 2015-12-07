package com.metaconnect.googleauthentication.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.googleauthentication.services.GoogleAuthenticationService;
import com.metaconnect.group.model.Group;
import com.metaconnect.group.service.GroupService;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.notification.service.NotificationService;
import com.metaconnect.post.model.Post;
import com.metaconnect.post.service.PostService;
import com.metaconnect.user.model.User;
import com.metaconnect.user.service.UserService;

/**
 * @author MetaConnect
 *
 */
@Controller
public class GoogleAuthenticationController {

	@Autowired
	private GoogleAuthenticationService service;
	@Autowired
	private PostService postService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;

	/**
	 * @param request --object of HttpServletRequest to create the session
	 * @return will return to modelAndView to the index if the user has entered
	 * wrong id and if the the id is correct then it will login to the home page
	 * and if user is already existing in database than it will go to home page with 
	 * all the requested parameters
	 * and if the user is new than it will redirect to the page where user have to join a 
	 * group
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/OAuth2", method = RequestMethod.GET)
	public ModelAndView GoogleOauth(Map<String, Object> map,
			HttpServletRequest request) {
		String redirectUri = "index";
		String code = request.getParameter("code");
		ModelAndView model = new ModelAndView();
		if (code != null) {

			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");
			redirectUri = "Profile";
			if (user == null) {
				user = service.GoogleAuthenticationServices(code);
				session = request.getSession();
				session.setAttribute("user",user);
				User temp=(User) session.getAttribute("user");
			}

			Set<Group> groups = userService.getMyGroup(user);
			if (groups.size() != 0) {
				Post post = new Post();
				Comments comments=new Comments();
				model.addObject("comment",comments);
				model.addObject("newPost", post);
				model.addObject("allgroups", groups);
				Set<Post> posts=postService.getAllPostOnHome(user,0,5);
				model.addObject("allPosts", posts);	
				Set<GroupMember> groupRequest = userService.getAllRequest(user);
				Set<Notification> notificationList = notificationService.getAllNotification(user);
				model.addObject("notificationList", notificationList);
		
				model.addObject("request", groupRequest);
				redirectUri = "Home";
			}

		}
		model.setViewName(redirectUri);
		return model;

	}

}
