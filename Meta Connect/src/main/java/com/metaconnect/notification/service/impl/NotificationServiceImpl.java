package com.metaconnect.notification.service.impl;




import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.group.model.Group;
import com.metaconnect.notification.dao.NotificationDao;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.notification.service.NotificationService;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;
import com.metaconnect.user.service.UserService;

/**
 * @author MetaConnect
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private UserService userService;
	
	/* (non-Javadoc)
	 * @see com.metaconnect.notification.service.NotificationService#add(com.metaconnect.post.model.Post)
	 * will call notificationDao method to add the notification
	 */
	@Transactional
	public void add(Post post) {
	 Notification notification = new Notification();
		notification.setGroupId(post.getGroupId());
		notification.setPostId(post);
		notificationDao.add(notification);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.notification.service.NotificationService#getAllNotification(com.metaconnect.user.model.User)
	 * will return the notification for the user who has logged in and to the group in which the user is added.
	 */
	@Transactional
	public Set<Notification> getAllNotification(User user) {
		Set<Group> userGroup = userService.getMyGroup(user);
		return notificationDao.getAllNotification(userGroup,user);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.notification.service.NotificationService#getNotification(int)
	 * will return the notification by id
	 */
	@Override
	public Notification getNotification(int id) {
		return notificationDao.getNotification(id);
	}

}
