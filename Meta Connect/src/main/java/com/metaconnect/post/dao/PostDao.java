package com.metaconnect.post.dao;



import java.util.List;
import java.util.Set;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.group.model.Group;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface PostDao {
	public void add(Post post);
	public void edit(Post post);
	public void delete(long postId);
	public Post getPost(long postId);
	public List<Post> getAllPost();
	public Post getLatestPost();
	public Set<Comments> getAllCommentOfUser(Post post) ;
	public Set<Post> getAllPostOnHome(User user,Set<Group> groups,int pageNo,int pageSize);
	
	
	
}
