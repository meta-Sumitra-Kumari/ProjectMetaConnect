package com.metaconnect.notification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.notification.dao.NotificationReadDao;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.notification.model.NotificationRead;
import com.metaconnect.notification.service.NotificationReadService;
import com.metaconnect.user.model.User;
/**
 * @author MetaConnect
 *
 */
@Service
public class NotificationReadServiceImpl implements NotificationReadService {

	@Autowired
	private NotificationReadDao notificationReadDao;
	/* (non-Javadoc)
	 * @see com.metaconnect.notification.service.NotificationReadService#add(com.metaconnect.user.model.User, com.metaconnect.notification.model.Notification)
	 * will set the setUserid to the user who has logged in
	 * and notification id to the id of the notification
	 */
	@Transactional
	public void add(User user, Notification notification) {
		NotificationRead notificationRead = new NotificationRead();
		notificationRead.setUserId(user);
		notificationRead.setNotificationId(notification.getNotificationId());
		notificationReadDao.add(notificationRead);
	}

}
