package com.metaconnect.group.service;

import java.util.List;
import java.util.Set;

import com.metaconnect.group.model.Group;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface GroupService {
	public void add(Group group);
	public void edit(Group group);
	public Group getGroup(long groupId);
	public List<Group> getAllGroup();
	public Set<Group> getMyGroup(User user);
	public Set<Post> getAllPostOfGroup(Group group);
	public Group getLatestGroup();
	}
