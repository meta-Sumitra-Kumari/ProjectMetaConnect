package com.metaconnect.user.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.group.model.Group;
import com.metaconnect.groupmembers.dao.GroupMemberDao;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.post.dao.PostDao;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;
import com.metaconnect.user.service.UserService;

/**
 * @author MetaConnect
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private GroupMemberDao groupMemberDao;

	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#add(com.metaconnect.user.model.User)
	 * will update the user with all the entry of the user to the database.
	 * if user is present in the the database
	 * else it will add the user to the database
	 */
	@Transactional
	public User add(User user) {

		User existsuser = userDao.getUserByEmail(user);

		if (existsuser != null) {
			  user.setAddress(existsuser.getAddress());
			  user.setPhoneNumber(existsuser.getPhoneNumber());
	          user.setFirstName(existsuser.getFirstName());
	          user.setLastName(existsuser.getLastName());
	          user.setGender(existsuser.getGender());
			  user.setUserId(existsuser.getUserId());
	          user.setGroupMembers(existsuser.getGroupMembers());
	          user.setUserComments(existsuser.getUserComments());
	          user.setUserPosts(existsuser.getUserPosts());
	          userDao.edit(user);

		} 
		else {
			
			existsuser = userDao.add(user);
		}
		return existsuser;
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#edit(com.metaconnect.user.model.User)
	 	will edit the user
	 */
	@Transactional
	public void edit(User user) {
		userDao.edit(user);

	}


	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#getUser(long)
	 * will get the user by userid 
	 */
	@Transactional
	public User getUser(long userId) {

		return userDao.getUser(userId);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#getAllUser()
	 * will return the list of all users
	 */
	@Transactional
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#getMyGroup(com.metaconnect.user.model.User)
	 * will return set of all groups of logged in user
	 */
	@Override
	public Set<Group> getMyGroup(User user) {
		Set<GroupMember> groupMembers=groupMemberDao.getAllJoinedGroup(user);
		Set<Group> groups = new HashSet<Group>();
		if(groupMembers!=null){
		for (GroupMember groupmember : groupMembers) {
			groups.add(groupmember.getGroupId());

		}
		}
		return groups;
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#updateSession(com.metaconnect.user.model.User, javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("unused")
	@Transactional
	public void updateSession(User user,HttpServletRequest request) {
		HttpSession session =  request.getSession(false);
		User updatedUser=userDao.getUser(user.getUserId());
		session.setAttribute("user", user);
		
		
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#getAllRequest(com.metaconnect.user.model.User)
	 * will return the set of all request for the logged in user from groupmemberDao
	 */
	@Transactional
	public Set<GroupMember> getAllRequest(User user) {
   return groupMemberDao.getAllRequest(user);
		
	}
	
	/* (non-Javadoc)
	 * @see com.metaconnect.user.service.UserService#search(java.lang.String, com.metaconnect.user.model.User)
	 * will return th set of all the post which the user has searched for
	 */
	@Transactional
	public Set<Post> search(String search, User user) {

		Set<Post> searchPosts = new HashSet<Post>();
		
		  Set<Group> groups = getMyGroup(user);
		  for (Group group : groups) {
			  Set<Post> posts = group.getGroupPosts(); 
			  for (Post  post : posts) {
				  if (post.getPostDescription().contains(search)) {
		                searchPosts.add(post);
		             }
				  else {
						Set<Comments> comments = postDao.getAllCommentOfUser(post);
						for (Comments comment : comments) {
							if (comment.getCommentDescription().contains(search)) {
								searchPosts.add(post);
	
							}

						}

					}
			  }
			  
			  }
		 
  	
		
		return searchPosts;

	}

}
