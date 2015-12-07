package com.metaconnect.group.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.metaconnect.Validator.GroupValidator;
import com.metaconnect.comment.model.Comments;
import com.metaconnect.group.model.Group;
import com.metaconnect.group.service.GroupService;
import com.metaconnect.groupmembers.service.GroupMemberService;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@SessionAttributes("user")
@Controller
public class GroupController {

	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupMemberService groupMembers;

	@Autowired
	private GroupValidator groupValidator;

	@InitBinder("group")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(groupValidator);
	}

	/**
	 * @return will send to the createGroup page to create new group
	 */
	@RequestMapping("createGroup")
	public ModelAndView setupForm() {
		ModelAndView modelAndView = new ModelAndView("createGroup");
		modelAndView.addObject("group",new Group());
		modelAndView.addObject("groupList", groupService.getAllGroup());
		return modelAndView;
	}

	/**
	 * @param user -- user who logged in
	 * @param group --when group is created the object of group will be send
	 * @param result --to bind the object
	 * @return 
	 * 	will redirect to the home page of group add some thing to group.
	 */
	@RequestMapping(value = "createGroup", method = RequestMethod.POST)
	public ModelAndView addGroup(@ModelAttribute("user") User user,
			@ModelAttribute @Validated Group group, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		
		String redirectUri = "groupHome";
		if (result.hasErrors()) {
			redirectUri = "createGroup";
			modelAndView.setViewName(redirectUri);
		} else {
			group.setGroupAdminId(user);
			modelAndView.setViewName(redirectUri);
			groupMembers.addGroupAdmin(group, user);
			group = groupService.getLatestGroup();
			modelAndView.addObject("group", group);
			modelAndView.addObject("newPost", new Post());
		}
		return modelAndView;
	}

	/**
	 * @param id -- id of the group which we want to edit 
	 * 
	 * @return will return to the editgroup Page with the object of group who you want to edit
	 */
	@RequestMapping(value = "editGroup", method = RequestMethod.GET)
	public ModelAndView editGroup(@RequestParam("groupid") long id,@ModelAttribute User user) {

		ModelAndView modelAndView = new ModelAndView("editGroup");
		Group groupResult=groupService.getGroup(id);
		if(user.getUserId()!=groupResult.getGroupAdminId().getUserId())
		{
        modelAndView.addObject("message","You are not accessable to this group");			
		}

		modelAndView.addObject("group", groupResult);
		return modelAndView;
	}
	
	/**
	 * @param id -- id of the group which we have edited 
	 * @param group -- object of the group
	 * @return will return to the home page of the group after editing
	 * will all the list of post and list of comment 
	 * and new object for the post and for comment
	 */
	@RequestMapping(value = "updateGroup", method = RequestMethod.POST)
	public ModelAndView updateGroup(@RequestParam("groupid") long id,@ModelAttribute Group group) {
		ModelAndView modelAndView = new ModelAndView("groupHome");
		Group groupResult=groupService.getGroup(id);
		group.setGroupAdminId(groupResult.getGroupAdminId());
		group.setGroupId(id);
		groupService.edit(group);
		Group updatedGroup=groupService.getGroup(id);
		modelAndView.addObject("group", updatedGroup);
		Set<Post> postsOfGroup = groupService.getAllPostOfGroup(group);
		modelAndView.addObject("postOfGroup", postsOfGroup);
		modelAndView.addObject("newPost",new  Post());
		modelAndView.addObject("comment", new Comments());
		return modelAndView;
	}

	/**
	 * @param group object of the group
	 * @param user-- user who is logged in 
	 * @return will return the set of all the groups in which user is added from 
	 * getMyGroup of groupService method
	 */
	@RequestMapping(value = "getMyGroup", method = RequestMethod.GET)
	public ModelAndView getMyGroup(@ModelAttribute Group group,
			@ModelAttribute("user") User user) {
		ModelAndView modelAndView = new ModelAndView("MyGroup");
		Set<Group> mygroup = groupService.getMyGroup(user);
		modelAndView.addObject("mygroups", mygroup);
		return modelAndView;
	}

	/**
	 * @param groupId -- id of the group to which user want to view
	 * @param user -- user who is logged in
	 * 
	 * @return will return to the groupHomeForRequest to from where user can request
	 */
	@RequestMapping(value = "groupPage", method = RequestMethod.GET)
	public ModelAndView getGroupPage(@RequestParam("groupid") int groupId,
			@ModelAttribute("user") User user) {
		Group group = groupService.getGroup(groupId);
		ModelAndView modelAndView = new ModelAndView("groupHomeForRequest");
		Set<Post> postsOfGroup = groupService.getAllPostOfGroup(group);
		modelAndView.addObject("postOfGroup", postsOfGroup);
		modelAndView.addObject("group", group);
		return modelAndView;
	}
	/**
	 * @param groupId --group id which user want to view
	 * @param user -- user who is logged in
	 * 
	 * @return will redirect to the groupHome with all the post and all the comment made on the post 
	 */

	@RequestMapping(value = "groupPageForUserInGroup", method = RequestMethod.GET)
	public ModelAndView getGroupPageForUserInGroup(
			@RequestParam("groupid") long groupId,
			@ModelAttribute("user") User user) {
		Group group = groupService.getGroup(groupId);
		ModelAndView modelAndView = new ModelAndView("groupHome");
		Set<Post> postsOfGroup = groupService.getAllPostOfGroup(group);
		modelAndView.addObject("postOfGroup", postsOfGroup);
		modelAndView.addObject("newPost",new Post());
		modelAndView.addObject("comment", new Comments());
		modelAndView.addObject("group", group);
    	return modelAndView;
	}

	/**
	 * @param user-- user who is logged in
	 * @return will return to the page where a list of those groups will be
	 * shown in which user is not added.
	 */
	@RequestMapping(value = "joinagroup", method = RequestMethod.GET)
	public ModelAndView getGrouList(@ModelAttribute("user") User user) {
		ModelAndView modelAndView = new ModelAndView("joinGroup");
		Set<Group> groups = groupMembers.getAllRequestedGroup(user);
		modelAndView.addObject("groups", groups);
		return modelAndView;
	}
	
	

}
