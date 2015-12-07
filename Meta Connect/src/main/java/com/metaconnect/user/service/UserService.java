package com.metaconnect.user.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.metaconnect.group.model.Group;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface UserService {
	public User add(User user);
	public void edit(User user);
	public User getUser(long userId);
	public List<User> getAllUser();
	public Set<Group> getMyGroup(User user);
	public void updateSession(User user,HttpServletRequest request);
	public Set<GroupMember> getAllRequest(User user);
	public Set<Post> search(String search, User user);
}
