package com.metaconnect.notification.service;


import java.util.Set;

import com.metaconnect.notification.model.Notification;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface NotificationService {
	public void add(Post post);
	public Set<Notification> getAllNotification(User user);
	public Notification getNotification(int id);
}
