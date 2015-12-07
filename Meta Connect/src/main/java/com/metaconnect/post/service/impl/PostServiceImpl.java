package com.metaconnect.post.service.impl;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.metaconnect.group.model.Group;
import com.metaconnect.group.service.GroupService;
import com.metaconnect.post.dao.PostDao;
import com.metaconnect.post.model.Post;
import com.metaconnect.post.service.PostService;
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private GroupService groupService;

	/* (non-Javadoc)
	 * @see com.metaconnect.post.service.PostService#add(com.metaconnect.post.model.Post)
	 * will call the add method od postDao object
	 */
	@Transactional
	public void add(Post post) {
		postDao.add(post);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.service.PostService#edit(com.metaconnect.post.model.Post)
	 * will call the method of postDao to edit the post
	 */
	@Transactional
	public void edit(Post post) {
		postDao.edit(post);

	}

	
	/* (non-Javadoc)
	 * @see com.metaconnect.post.service.PostService#getPost(long)
	 * will call the method of postDao to get the post by postId
	 */
	@Transactional
	public Post getPost(long postId) {

		return postDao.getPost(postId);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.service.PostService#getAllPost()
	 * will call the podtDao method to get All the post.
	 */
	@Transactional
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return postDao.getAllPost();
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.service.PostService#getAllPostOfUser(com.metaconnect.user.model.User)
	 * will call the userDao to get all the post of logged in user 
	 */
	@Transactional
	public Set<Post> getAllPostOfUser(User user) {

		return userDao.getAllPostsOfUser(user);
	}


	/* (non-Javadoc)
	 * @see com.metaconnect.post.service.PostService#getLatestPost()
	 * will call postdao method to get the latest post
	 */
	@Transactional
	public Post getLatestPost() {
		return postDao.getLatestPost();
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.service.PostService#getAllPostOnHome(com.metaconnect.user.model.User, int, int)
	 * will call the postDao method getAllPostOnHome with the parameter user-- who is logged in 
	 * groups-- in which user is added
	 * and number of post want to send on page. 
	 */
	@Transactional
	public Set<Post> getAllPostOnHome(User user, int pageNo, int pageSize) {
		Set<Group> groups = groupService.getMyGroup(user);
		return postDao.getAllPostOnHome(user, groups, pageNo * pageSize, (pageNo * pageSize)+pageSize);
	}
}
