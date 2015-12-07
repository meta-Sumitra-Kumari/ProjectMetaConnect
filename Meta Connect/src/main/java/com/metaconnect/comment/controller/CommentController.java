package com.metaconnect.comment.controller;

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
import com.metaconnect.comment.service.CommentService;
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
@SessionAttributes("user")
@Controller
public class CommentController {
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private PostService postService;
	@Autowired

	private UserService userService;

	/**
	 * @param user-- user who is logged in
	 * @param id --id of the post where user has commented
	 * @param comment-- comment which is to save on post
	 * @param request -- to update the user
	 * @return will redirect to the same page from where user has commented
	 * with all the required parameters
	 */
	@RequestMapping(value = "addComment", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("user") User user,
			@RequestParam("id") int id, @ModelAttribute Comments comment,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("commentSnippet");
		Post post = postService.getPost(id);
		Set<Post> posts = postService.getAllPostOfUser(user);
		modelAndView.addObject("postlist", posts);
		commentService.add(comment, user, post);
		Comments commentResult = commentService.getLastCOmment();
		notificationService.add(commentResult.getPostId());
		userService.updateSession(comment.getUserId(), request);
		modelAndView.addObject("comment", commentResult);
		return modelAndView;
	}
	/**
	 * @param user--user who has logged in
	 * @param id-- id of the post where user has commented on home
	 * @param comment-- object of comment which is to store
	 * @param request-- request object to update the user
	 * @return will return to home page with all the required attributes.
	 */

	@RequestMapping(value = "addCommentAtHome", method = RequestMethod.POST)
	public ModelAndView addCommentAT(@ModelAttribute("user") User user,
			@RequestParam("id") int id, @ModelAttribute Comments comment,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("Home");
		Post post = postService.getPost(id);
		Set<Post> posts = postService.getAllPostOfUser(user);
		modelAndView.addObject("postlist", posts);
		commentService.add(comment, user, post);
		userService.updateSession(comment.getUserId(), request);
		modelAndView.addObject("post", post);
		modelAndView.addObject("comment", new Comments());
		modelAndView.addObject("commentList", commentService.getAllComment());
		Set<Notification> notificationList = notificationService.getAllNotification(user);
		modelAndView.addObject("notificationList", notificationList);

		return modelAndView;
	}

	/**
	 * @param id-- id of comment who who want to edit
	 * @param user -- user who is logged in
	 * @param result -- to update the user session
	 * @return -- will redirect to editCommentPage where the object of comment is passed
	 * 
	 */

	@RequestMapping(value = "updateComment", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("commentid") long id,
			@ModelAttribute("user") User user, BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("pageForEditComment");
		Comments comment = commentService.getComment(id);
		if(user.getUserId()==comment.getUserId().getUserId())
		{
			modelAndView.addObject("comment", comment);
		}
		else{
			modelAndView.addObject("message","you are not access to this comment");
		}
		return modelAndView;
	}

	/**
	 * @param id-- id of the comment which is edited
	 * @param comment-- object of the comment 
	 * @param user-- user who is logged in
	 * @return will redirect to home page of the user after the comment is edited
	 * will all the required attributes
	 */
	@RequestMapping(value = "updateComment", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("commentid") long id,
			@ModelAttribute Comments comment,
			@ModelAttribute("user") User user) {
		Comments previousComment = commentService.getComment(id);
		comment.setPostId(previousComment.getPostId());
		comment.setCommentId(previousComment.getCommentId());
		comment.setUserId(user);
		commentService.edit(comment);
		ModelAndView model = new ModelAndView("Home");
		Set<Group> groups = userService.getMyGroup(user);
		model.addObject("comment", new Comments());
		model.addObject("newPost", new Post());
		model.addObject("allgroups", groups);
		Set<Post> posts = postService.getAllPostOnHome(user, 0, 5);
		model.addObject("allPosts", posts);
		Set<GroupMember> groupRequest = userService.getAllRequest(user);
		model.addObject("request", groupRequest);
		return model;
	}
	/**
	 * @param id-- id of the comment which user want to edit
	 * @param user-- user who is logged in
	 * @return after deleting the comment the user will redirect to home page 
	 * with all the required parameters
	 */
	@RequestMapping(value = "deleteComment", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("commentid") long id,
			@ModelAttribute("user") User user) {
		Comments comment = commentService.getComment(id);
		comment.setCommentStatus(1);
		commentService.edit(comment);
		ModelAndView model = new ModelAndView("Home");
		Set<Group> groups = userService.getMyGroup(user);
		model.addObject("comment", new Comments());
		model.addObject("newPost", new Post());
		model.addObject("allgroups", groups);
		Set<Post> posts = postService.getAllPostOnHome(user, 0, 5);
		model.addObject("allPosts", posts);
		Set<GroupMember> groupRequest = userService.getAllRequest(user);
		model.addObject("request", groupRequest);
		return model;
	}
}
