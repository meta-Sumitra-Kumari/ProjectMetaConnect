package com.metaconnect.post.service;

import java.util.List;
import java.util.Set;

import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface PostService {
	public void add(Post post);
	public void edit(Post post);
	public Post getPost(long postId);
	public List<Post> getAllPost();
	public Set<Post> getAllPostOfUser(User user);
	public Post getLatestPost();
	public Set<Post> getAllPostOnHome(User user,int pageNo,int pageSize);
	
}
