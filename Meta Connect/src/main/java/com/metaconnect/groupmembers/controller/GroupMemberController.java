package com.metaconnect.groupmembers.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.metaconnect.group.model.Group;
import com.metaconnect.group.service.GroupService;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.groupmembers.service.GroupMemberService;
import com.metaconnect.user.model.User;
/**
 * @author MetaConnect
 *
 */
@SessionAttributes("user")
@Controller
public class GroupMemberController {

	@Autowired
	private GroupMemberService groupMemberService;
	
	@Autowired
	private GroupService groupService;
	
	/**
	 * @param id -- id of the group for which user has requested.
	 * @param user -- user who is logged in
	 * @param groupMember --the object of groupMember class to connect
	 *  by clicking on the group name for which user want to request 
	 *  will direct to a page where group information will be displayed
	 * @return will send the modelAndView Object to the groupHomeForRequest.
	 */
	@RequestMapping(value = "addMember" , method = RequestMethod.POST)
	public ModelAndView add(@RequestParam("id") long id,@ModelAttribute("user") User user,@ModelAttribute GroupMember groupMember){
		
		ModelAndView modelAndView = new ModelAndView("groupHomeForRequest");
		Group group= groupService.getGroup(id);
		groupMember.setUserId(user);
		groupMember.setGroupId(group);
		groupMember.setSeen(false);
		groupMemberService.add(groupMember);
		modelAndView.addObject("group", group);
		modelAndView.addObject("groupMemberList", groupMemberService.getAllGroupMember());
		return modelAndView;
	}
	
	/**
	 * @param id -- id of the group whose's member we want to view.
	 * @return will return to the showGroupMember Page in which the list of whole group members will be printed.
	 */
	@RequestMapping(value = "groupMembers" , method = RequestMethod.GET)
	public ModelAndView showGroupMembers(@RequestParam("groupid") long id){
		ModelAndView modelAndView = new ModelAndView("ShowGroupMember");
		Group group = groupService.getGroup(id);
		Set<GroupMember> groupMember = groupMemberService.getGroupMember(group);
		modelAndView.addObject("group", group);
		modelAndView.addObject("groupMemberList", groupMember);
		return modelAndView;
	}
	
	
	

}
