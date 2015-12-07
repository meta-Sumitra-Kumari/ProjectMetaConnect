package com.metaconnect.group.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.group.dao.GroupDao;
import com.metaconnect.group.model.Group;
import com.metaconnect.group.service.GroupService;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;
import com.metaconnect.user.service.UserService;

/**
 * @author MetaConnect
 *
 */
@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;


	/* (non-Javadoc)
	 * will call the groupDao method to add the group
	 * @see com.metaconnect.group.service.GroupService#add(com.metaconnect.group.model.Group)
	 */
	@Transactional
	public void add(Group group) {
		groupDao.add(group);

	}

	/* (non-Javadoc)
	 * will call the groupDao to edit the group
	 * @see com.metaconnect.group.service.GroupService#edit(com.metaconnect.group.model.Group)
	 */
	@Transactional
	public void edit(Group group) {
		groupDao.edit(group);

	}

	/* (non-Javadoc)
	 * will call groupDao to get the group by id
	 * @see com.metaconnect.group.service.GroupService#getGroup(long)
	 */
	@Transactional
	public Group getGroup(long groupId) {
		return groupDao.getGroup(groupId);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.group.service.GroupService#getAllGroup()
	 * will call groupDao to getAllGroup
	 */
	@Transactional
	public List<Group> getAllGroup() {
		return groupDao.getAllGroup();
	}

	/* (non-Javadoc)
	 * will call userService to get all the groups of user who has logged in
	 * @see com.metaconnect.group.service.GroupService#getMyGroup(com.metaconnect.user.model.User)
	 */
	@Transactional
	public Set<Group> getMyGroup(User user) {
		return userService.getMyGroup(user);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.group.service.GroupService#getLatestGroup()
	 * will call groupDao method to get the last added group
	 */
	@Transactional
	public Group getLatestGroup() {
		return groupDao.getLatestGroup();
	}

	/* (non-Javadoc)
	 * will call groupDao method to get All the post of group for which requested.
	 * @see com.metaconnect.group.service.GroupService#getAllPostOfGroup(com.metaconnect.group.model.Group)
	 */
	@Transactional
	public Set<Post> getAllPostOfGroup(Group group) {
		return groupDao.getAllPostOfGroup(group);
	}

}
