package com.metaconnect.user.dao;



import java.util.List;
import java.util.Set;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface UserDao {
	public User add(User user);
	public void edit(User user);
	public User getUser(long userId);
	public List<User> getAllUser();
	public User getUserByEmail(User user);
	public Set<Post> getAllPostsOfUser(User user);
	
}
