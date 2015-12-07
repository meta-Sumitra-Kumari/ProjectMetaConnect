package com.metaconnect.group.dao.impl;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metaconnect.group.dao.GroupDao;
import com.metaconnect.group.model.Group;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Repository
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private SessionFactory session;


	/* (non-Javadoc)
	 * will add the group to database
	 * @see com.metaconnect.group.dao.GroupDao#add(com.metaconnect.group.model.Group)
	 */
	@Override
	public void add(Group group) {
		session.getCurrentSession().save(group);
	}
	
	/* (non-Javadoc)
	 * will edit the the group
	 * @see com.metaconnect.group.dao.GroupDao#edit(com.metaconnect.group.model.Group)
	 */
	@Override
	public void edit(Group group) {
		session.getCurrentSession().merge(group);
	}

	/* (non-Javadoc)
	 * will get the group by groupId
	 * @see com.metaconnect.group.dao.GroupDao#getGroup(long)
	 */
	@Override
	public Group getGroup(long groupId) {
		return (Group) session.getCurrentSession().get(Group.class, groupId);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.group.dao.GroupDao#getAllGroup()
	 * will return all groups
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAllGroup() {
		return session.getCurrentSession().createQuery("from Group").list();
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.group.dao.GroupDao#getAllGroupForRequest(com.metaconnect.user.model.User)
	 * will return the list of those's group for which user can request
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Group> getAllGroupForRequest(User user) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				Group.class);
		newUsers.add(Restrictions.not(Restrictions.eq("groupAdminId", user)));
		Set<Group> groups = new LinkedHashSet<Group>(newUsers.list());
		return groups;
	}

	/* (non-Javadoc)
	 * will return the list of those post which are made on group
	 * @see com.metaconnect.group.dao.GroupDao#getAllPostOfGroup(com.metaconnect.group.model.Group)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Post> getAllPostOfGroup(Group group) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				Post.class);
		newUsers.add(Restrictions.eq("groupId", group));
		newUsers.add(Restrictions.eq("postStatus", 0));
		newUsers.addOrder(Order.desc("postCreatedTime"));
		Set<Post> posts = new LinkedHashSet<Post>(newUsers.list());
		return posts;
	}

	/* (non-Javadoc)
	 * will return the list of those group for which logged in user can accept 
	 * request. 
	 * @see com.metaconnect.group.dao.GroupDao#getAllGroupForRequestAccept(com.metaconnect.user.model.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Group> getAllGroupForRequestAccept(User user) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				Group.class);
		newUsers.add(Restrictions.eq("groupAdminId", user));
		Set<Group> groups = new LinkedHashSet<Group>(newUsers.list());
		return groups;
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.group.dao.GroupDao#getLatestGroup()
	 * will return the latest group which is created.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Group getLatestGroup() {
		List<Group> groups =  session.getCurrentSession().createQuery("from Group order by groupId DESC LIMIT 1").list();
		if(groups.size()!=0)
		{
			return groups.get(0);
		}
		return null;
	}

}
