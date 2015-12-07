package com.metaconnect.notification.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metaconnect.notification.dao.NotificationReadDao;
import com.metaconnect.notification.model.NotificationRead;
/**
 * @author MetaConnect
 *
 */
@Repository
public class NotificationReadDaoImpl implements NotificationReadDao {

	@Autowired
	private SessionFactory session;
	
	/* (non-Javadoc)
	 * @see com.metaconnect.notification.dao.NotificationReadDao#add(com.metaconnect.notification.model.NotificationRead)
	 * will save the notificationRead to the table the entry of the user who has read the notification
	 */
	@Override
	public void add(NotificationRead notificationRead) {
		session.getCurrentSession().save(notificationRead);

	}

}
