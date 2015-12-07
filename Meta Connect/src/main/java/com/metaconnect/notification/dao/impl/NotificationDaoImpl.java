package com.metaconnect.notification.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import com.metaconnect.group.model.Group;
import com.metaconnect.notification.dao.NotificationDao;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.notification.model.NotificationRead;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Transactional
@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SessionFactory session;
	
	/* (non-Javadoc)
	 * @see com.metaconnect.notification.dao.NotificationDao#add(com.metaconnect.notification.model.Notification)
	 * will save the notifictaion to dtabase
	 */
	@Override
	public void add(Notification notification) {
		session.getCurrentSession().save(notification);
	}
	
	/* (non-Javadoc)
	 * @see com.metaconnect.notification.dao.NotificationDao#getAllNotification(java.util.Set, com.metaconnect.user.model.User)
	 * will return the set of all notification to the user
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Notification> getAllNotification(Set<Group> userGroup, User user) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				Notification.class);
		Set<Notification> notificationforUser=null;
		if(userGroup!=null && userGroup.size()!=0 )
		{		
		newUsers.add(Restrictions.in("groupId", userGroup));
		newUsers.addOrder(Order.desc("notificationCreatedTime"));
		Criteria newUser = session.getCurrentSession().createCriteria(
				NotificationRead.class);
		newUser.add(Restrictions.eq("userId", user));
		newUser.setProjection(Projections.property("notificationId"));
		List<Notification> result1 = newUser.list();

	
		if( result1!=null && result1.size()!=0){
				
		newUsers.add(Restrictions.not(Restrictions.in("notificationId", result1)));

	
		}
		List<Notification> results = newUsers.list();
		if(results.size()!=0)
		{
		notificationforUser = new LinkedHashSet<Notification>(results);
		}
		}
		return notificationforUser;
	}
	
	/* (non-Javadoc)
	 * @see com.metaconnect.notification.dao.NotificationDao#getNotification(int)
	 * will return the notification by its id.
	 */
	/* (non-Javadoc)
	 * @see com.metaconnect.notification.dao.NotificationDao#getNotification(int)
	 */
	@Override
	public Notification getNotification(int id) {
		return (Notification) session.getCurrentSession().get(Notification.class, id);
	}

}
