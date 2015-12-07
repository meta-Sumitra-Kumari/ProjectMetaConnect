package com.metaconnect.user.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metaconnect.post.model.Post;
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory session;

	/* (non-Javadoc)
	 * wil save the object to the database
	 * @see com.metaconnect.user.dao.UserDao#add(com.metaconnect.user.model.User)
	 */
	@Override
	public User add(User user) {
		session.getCurrentSession().save(user);
		return user;

	}

	/* (non-Javadoc)
	 * will edit the user row in database
	 * @see com.metaconnect.user.dao.UserDao#edit(com.metaconnect.user.model.User)
	 */
	@Override
	public void edit(User user) {

		session.getCurrentSession().merge(user);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.user.dao.UserDao#getUser(long)
	 * will return the user entry from the user table.
	 */
	@Override
	public User getUser(long userId) {

		return (User) session.getCurrentSession().get(User.class, userId);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.user.dao.UserDao#getAllUser()
	 * will return the list of all users present in table
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {

		return session.getCurrentSession().createQuery("from User").list();
	}

	/* (non-Javadoc)
	 * will return the user who is searched by the email
	 * @see com.metaconnect.user.dao.UserDao#getUserByEmail(com.metaconnect.user.model.User)
	 */
	@Override
	public User getUserByEmail(User user) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				User.class);
		newUsers.add(Restrictions.eq("email", user.getEmail()));

		User existingUser = (User) newUsers.uniqueResult();

		return existingUser;
	}

	/* (non-Javadoc)
	 * will return all the post of the user who is loged in and thos post who are active in database.
	 * @see com.metaconnect.user.dao.UserDao#getAllPostsOfUser(com.metaconnect.user.model.User)
	 */
	@SuppressWarnings("unchecked")
	@Override	
	public Set<Post> getAllPostsOfUser(User user) {

		Criteria newUsers = session.getCurrentSession().createCriteria(
				Post.class);
		newUsers.add(Restrictions.eq("userId", user));
		newUsers.add(Restrictions.eq("postStatus", 0));
		Set<Post> post = new LinkedHashSet<Post>(newUsers.list());
		return post;
	}

}
