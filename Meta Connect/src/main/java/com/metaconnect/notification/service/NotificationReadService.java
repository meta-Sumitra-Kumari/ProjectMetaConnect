package com.metaconnect.notification.service;

import com.metaconnect.notification.model.Notification;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface NotificationReadService {

	public void add(User user, Notification notification);
}
