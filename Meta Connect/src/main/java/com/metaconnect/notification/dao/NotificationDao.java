package com.metaconnect.notification.dao;




import java.util.Set;

import com.metaconnect.group.model.Group;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface NotificationDao {
public void add(Notification notification);
public Set<Notification> getAllNotification(Set<Group> userGroup, User user);
public Notification getNotification(int id);

}
