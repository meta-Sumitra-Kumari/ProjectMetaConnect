package com.metaconnect.user.Controller;

import java.util.Set;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.group.model.Group;
import com.metaconnect.group.service.GroupService;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.groupmembers.service.GroupMemberService;
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
@SessionAttributes("user")
@Controller
public class UserController {

	private static int pageNo = 1;

	private static final int pageSize = 5;

	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;

	@Autowired
	private GroupMemberService groupMemberService;
	
	@Autowired
	private GroupService groupService;

	@Autowired
	private NotificationService notificationService;

	/**
	 * @param user the loged in user
	 * @return will redirect to the profileform page to comp[lete the profile
	 */
	@RequestMapping(value="editUser",method = RequestMethod.GET)
	public ModelAndView setupForm(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();
		model.setViewName("profileform");

		return model;

	}

	/**
	 * @param id of the user whose profile we want to view
	 * @return will retirect to the page viewUser and the object of the request user is send with name viewUser
	 */
	@RequestMapping("viewProfile")
	public ModelAndView viewProfile(@RequestParam("userid") long id) {
		ModelAndView model = new ModelAndView();
		User viewUser = userService.getUser(id);
		model.addObject("viewUser", viewUser);
		model.setViewName("viewUser");

		return model;

	}

	/**
	 * @param user user who is loged in
	 *  this controller is called when the user will fill form and will submit the page
	 *  after submitting the page ot will send to viewUser page with user object.
	 *  
	 */
	@RequestMapping(value = "editUser", method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute User user) {
		ModelAndView modelAndView = new ModelAndView("viewUser");
		userService.edit(user);
		modelAndView.addObject("userList", userService.getAllUser());
		modelAndView.addObject("viewUser",user);
		return modelAndView;
	}

	/**
	 * @param user user who is loged in
	 *  this controller will be called when the user will click on the request tab
	 *  and in this controller we are send the list of all request who are for the group whose admin is the user
	 * @return will redirect to the request page and at this page user can accept request or deny request.
	 */
	@RequestMapping("/requestAccept")
	public ModelAndView requestAccept(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView("requestPage");
		Set<GroupMember> groupRequest = userService.getAllRequest(user);
		model.addObject("request", groupRequest);
		return model;

	}

	/**
	 * @param user the user who is loged in
	 * @param id of the user who has send the request
	 *  whose request we have to access and accept
	 * @return	will return to the same page with the remaining request.
	 * 
	 */
	@RequestMapping(value = "acceptRequestForGroup", method = RequestMethod.POST)
	public ModelAndView requestAcceptForGroup(
			@ModelAttribute("user") User user, @RequestParam("id") int id) {
		ModelAndView model = new ModelAndView("requestPage");
		GroupMember groupMember = groupMemberService.getGroupMemberforRequest(id);
		groupMember.setSeen(true);
		groupMember.setStatus(1);
		groupMemberService.edit(groupMember);
		Set<GroupMember> groupRequest = userService.getAllRequest(user);
		model.addObject("request", groupRequest);
		return model;

	}

	/**
	 * @param user --user who is loged in
	 * @param id -- whose request we have to deny
	 * @return will return to the request page with the remaining request
	 */
	@RequestMapping(value = "deniedRequest", method = RequestMethod.POST)
	public ModelAndView requestDeniedForGroup(
			@ModelAttribute("user") User user, @RequestParam("id") long id) {
		ModelAndView model = new ModelAndView("requestPage");
		GroupMember groupMember = groupMemberService.getGroupMemberforRequest(id);
		groupMember.setStatus(-1);
		groupMemberService.edit(groupMember);
		Set<GroupMember> groupRequest = userService.getAllRequest(user);
		model.addObject("request", groupRequest);
		return model;

	}

	/**
	 * @param user -- who is logged in
	 * this page is called when user is we click on home tab.
	 * @return will return to the user home page with other object required on that page.
	 */
	@RequestMapping(value = "Home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("user") User user,HttpServletRequest request) {
		pageNo=1;
		String redirectUri = "createGroup";
		userService.updateSession(user, request);
		Set<Group> groups = userService.getMyGroup(user);
		ModelAndView model = homeObjects(groups, user);
		if (groups.size() != 0) {
			Set<Post> posts = postService.getAllPostOnHome(user,0,
					5);
			model.addObject("allPosts", posts);
		}
		else{
			model.addObject("group", new Group());
		model.setViewName(redirectUri);}
		return model;
	}

	/**
	 * @param user --user who is loged in
	 *  this controller is called when user click on load more button 
	 *  	will return the next 5 post to the user list of post 
	 * @return will redirect to the loadPost page. 
	 */
	@RequestMapping(value = "PostLoading", method = RequestMethod.GET)
	public ModelAndView PostLoading(@ModelAttribute("user") User user) {
		String redirectUri = "loadPost";
		Set<Group> groups = userService.getMyGroup(user);
		ModelAndView model = new ModelAndView();//homeObjects(groups, user);
		if (groups.size() != 0) {
			Set<Post> posts = postService.getAllPostOnHome(user, pageNo++,pageSize);
			model.addObject("loadmorePosts", posts);
			model.addObject("comment", new Comments());
		}
		
		model.setViewName(redirectUri);
		return model;

	}

	/**
	 * @param search -- the string that we have to search from post and comment
	 * @param user -- user who is loged in
	 * @param result to bind the object
	 *  this page will return those post in which the searched string is present
	 * @return will return to the Search page with the list of post.
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public ModelAndView search(
			@RequestParam(value = "search", required = true) String search,
			@ModelAttribute("user") User user, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("Search");
		Set<Post> posts = userService.search(search, user);
		if (posts.size() != 0) {
			modelAndView.addObject("comment", new Comments());
			modelAndView.addObject("postlist", posts);
			modelAndView.addObject("message", "Searching Results");

		} else {
			modelAndView.addObject("message", "Sorry No Result found");

		}
		return modelAndView;
	}

	/**
	 * @param user -- user who is loged in
	 * will be called when there will be no post for search result.
	 * @return will return to the search page with the mesaage no post for this search.
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView searchRedirect(@ModelAttribute("user") User user,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("Search");
		modelAndView.addObject("message", "Sorry No Result found");
		return modelAndView;
	}

	/**
	 * @param groups -- all the groups of the user
	 * @param user -- user who is loged in 
	 *  wil return the model object with the comment newPost and list of all groups
	 * @return
	 */
	private ModelAndView homeObjects(Set<Group> groups, User user) {
		ModelAndView model = new ModelAndView("Home");
		if (groups.size() != 0) {

			model.addObject("comment", new Comments());
			model.addObject("newPost", new Post());
			model.addObject("allgroups", groups);
			Set<GroupMember> groupRequest = userService.getAllRequest(user);
			model.addObject("request", groupRequest);
			Set<Notification> notificationList = notificationService.getAllNotification(user);
			model.addObject("notificationList", notificationList);
	
		}
		return model;
	}
}
