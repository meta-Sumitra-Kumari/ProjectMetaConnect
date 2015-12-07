package com.metaconnect.post.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.metaconnect.notification.model.Notification;
import com.metaconnect.notification.service.NotificationReadService;
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
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private NotificationReadService notificationReadService;


	/**
	 * @param id -- id of the group in which you want to post something
	 * @param post -- post attribute which you want to post
	 * @param user -- logged in user who has posted something
	 * @return will return to the postSnippet where the new added post will be set.
	 */
	@RequestMapping(value = "addPost", method = RequestMethod.POST)
	public ModelAndView add(@RequestParam("groupid") int id,
			@ModelAttribute Post post, @ModelAttribute("user") User user) {
		Group group = groupService.getGroup(id);
		ModelAndView modelAndView = new ModelAndView("postSnippet");
		post.setUserId(user);
		post.setGroupId(group);
		postService.add(post);

		Post lastPost = postService.getLatestPost();
		notificationService.add(lastPost);

		modelAndView.addObject("post", lastPost);
		modelAndView.addObject("group", group);
		modelAndView.addObject("newPost",new Post());
		modelAndView.addObject("comment", new Comments());
		return modelAndView;
	}

	/**
	 * @param id -- id of the post which you want to delete
	 * @param user -- logged in user
	 * @return will return to the home page after deleting the post
	 *  set of all the groups in which user is added
	 *  blank object of comment and post is send.
	 *  set of all the post which will be on the groups in which user is added.
	 */
	@RequestMapping(value = "deletePost", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("postid") long id,
			@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView("Home");
		Post deletepost = postService.getPost(id);
		deletepost.setPostStatus(1);
		postService.edit(deletepost);
		Set<Group> groups = userService.getMyGroup(user);
		Set<Post> posts = postService.getAllPostOnHome(user, 0, 5);
		Set<GroupMember> groupRequest = userService.getAllRequest(user);
		Set<Notification> notificationList = notificationService.getAllNotification(user);
		model.addObject("notificationList", notificationList);

		model.addObject("allPosts", posts);
		model.addObject("request", groupRequest);
		model.addObject("comment",new Comments());
		model.addObject("newPost", new Post());
		model.addObject("allgroups", groups);

		return model;
	}

	/**
	 * @param id -- id of the post which user want to edit
	 * @param user --logged in user
	 *  in this when the user will edit the post will redirect to a new page 
	 *  and the object of the post to edit is send.
	 * @return will sent to EditPost page.
	 */
	@RequestMapping(value = "updatePost", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("postid") long id,
			@ModelAttribute("user") User user) {

		ModelAndView modelAndView = new ModelAndView("EditPost");
		Post post = postService.getPost(id);
		if(user.getUserId()==post.getUserId().getUserId())
		{		modelAndView.addObject("post", post);
		}
		else{
			modelAndView.addObject("message","you are not access to this post");
		}
		return modelAndView;
	}

	/**
	 * @param id --id of the post which user has edited
	 * @param post --the pobject of the post which is edited
	 * @param user --logged in user
	 * after editing the post the home page will be displayed with all the previous post
	 * set of all the groups in which user has added.
	 * @return will return the model object
	 */
	@RequestMapping(value = "updatePost", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") long id,
			@ModelAttribute Post post, @ModelAttribute("user") User user) {

		ModelAndView model = new ModelAndView("Home");
		post.setPostId(id);
		Post latestPost = postService.getPost(id);
		post.setGroupId(latestPost.getGroupId());
		post.setUserId(user);
		postService.edit(post);
		Set<Group> groups = userService.getMyGroup(user);
		Set<GroupMember> groupRequest = userService.getAllRequest(user);
		Set<Notification> notificationList = notificationService.getAllNotification(user);
		model.addObject("notificationList", notificationList);
		model.addObject("post", post);
		model.addObject("comment", new Comments());
		model.addObject("newPost", new Post());
		model.addObject("allgroups", groups);
		Set<Post> posts = postService.getAllPostOnHome(user, 0, 5);
		model.addObject("allPosts", posts);
		model.addObject("request", groupRequest);
		return model;
	}

	/**
	 * @param post --Post object which will send all the post which user had made on any group in which user is added.
	 * 
	 * @param user -- logged in user
	 *  when user click on my post this page will be viewed and will show all the post
	 *  which user had done on any group.
	 *  
	 */
	@RequestMapping(value = "MyPost", method = RequestMethod.GET)
	public ModelAndView myPost(@ModelAttribute Post post,
			@ModelAttribute("user") User user) {
		Set<Post> posts = postService.getAllPostOfUser(user);
		ModelAndView modelAndView = new ModelAndView("mypost");
		if (posts != null && posts.size() != 0) {
			modelAndView.addObject("comment", new Comments());
		} else {
			modelAndView.addObject("errormessage", "You have No Post to view");
		}
		modelAndView.addObject("postlist", posts);
		return modelAndView;

	}
	
	/**
	 * @param post --Post object which will send all the post which user had made on any group in which user is added.
	 * 
	 * @param user -- logged in user
	 *  when user click on my post this page will be viewed and will show all the post
	 *  which user had done on any group.
	 * @return
	 *  will return the model object will the page mypost url
	 */
	@RequestMapping(value = "post", method = RequestMethod.GET)
	public ModelAndView myPost(@RequestParam("notificationid") int nid,
			@ModelAttribute("user") User user) {
		Notification notification = notificationService.getNotification(nid);
		Post posts = postService.getPost(notification.getPostId().getPostId());

		ModelAndView modelAndView = new ModelAndView("postPage");
		notificationReadService.add(user, notification);
		modelAndView.addObject("comment", new Comments());
		modelAndView.addObject("post", posts);
		return modelAndView;
	}

}
