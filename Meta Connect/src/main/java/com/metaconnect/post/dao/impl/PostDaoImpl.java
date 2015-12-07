package com.metaconnect.post.dao.impl;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.group.model.Group;
import com.metaconnect.post.dao.PostDao;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Repository
public class PostDaoImpl implements PostDao {

	@Autowired
	private SessionFactory session;
	
	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#add(com.metaconnect.post.model.Post)
	 * will save the post to the database
	 */
	@Override
	public void add(Post post) {
		session.getCurrentSession().save(post);

	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#edit(com.metaconnect.post.model.Post)
	 * will edit the post 
	 */
	@Override
	public void edit(Post post) {
		session.getCurrentSession().merge(post);

	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#getPost(long)
	 * will return the post by postId
	 */
	@Override
	public void delete(long postId) {
		session.getCurrentSession().delete(getPost(postId));


	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#getAllPost()
	 * will return all the post which have status 1
	 */
	@Override
	public Post getPost(long postId) {
		
		return (Post)session.getCurrentSession().get(Post.class, postId);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#getLatestPost()
	 * will send the last added post.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllPost() {
		
		return session.getCurrentSession().createQuery("from Post where post_status = 0").list();
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#getAllCommentOfUser(com.metaconnect.post.model.Post)
	 * will return the all comments made by user who has logged in
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Post getLatestPost() {
		List<Post> posts = session.getCurrentSession().createQuery("from Post order by postId DESC LIMIT 1").list();
		if(posts.size()!=0)
		{
			return posts.get(0);
		}
		return null;
}

	
	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#getAllCommentOfUser(com.metaconnect.post.model.Post)
	 * will return the set of all the comments made on post
	 */
	@Override
	public Set<Comments> getAllCommentOfUser(Post post) {
		Set<Comments> comment = new LinkedHashSet<Comments>(post.getPostsComments());
        return comment;
	}
	
	/* (non-Javadoc)
	 * @see com.metaconnect.post.dao.PostDao#getAllPostOnHome(com.metaconnect.user.model.User, java.util.Set, int, int)
	 * wil return the set of all post of those group in which user has added 
	 * and the post is active with status 0
	 * and the order for post is by descending 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Post> getAllPostOnHome(User user,Set<Group> groups,int fristPage,int lastPage) {
		
		Criteria newUsers = session.getCurrentSession().createCriteria(
				Post.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		newUsers.add(Restrictions.in("groupId", groups));
		newUsers.add(Restrictions.eq("postStatus",0));
		newUsers.addOrder(Order.desc("postCreatedTime"));
	    List<Post> posList=newUsers.list();
	    Set<Post> posts=new LinkedHashSet<Post>();
		int totalPost=posList.size();	
		
		if(lastPage>totalPost)
		{
			lastPage=totalPost;
		}
		for(int page=fristPage;page<lastPage;page++)
		
		{  
			 posts.add(posList.get(page));
		}
	   return posts;
    	}
	
	
}
