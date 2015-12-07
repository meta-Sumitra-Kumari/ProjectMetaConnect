package com.metaconnect.notification.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import com.metaconnect.group.model.Group;
import com.metaconnect.post.model.Post;


/**
 * @author MetaConnect
 *
 */
@Entity
@Table(name = "NOTIFICATION")
@Data
public class Notification {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notificationId;

    @Column(name = "notification_created_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp notificationCreatedTime;
    
   

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id") 
    private Group groupId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post postId;

    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		if (notificationCreatedTime == null) {
			if (other.notificationCreatedTime != null)
				return false;
		} else if (!notificationCreatedTime
				.equals(other.notificationCreatedTime))
			return false;
		if (notificationId != other.notificationId)
			return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((notificationCreatedTime == null) ? 0
						: notificationCreatedTime.hashCode());
		result = prime * result + notificationId;
		
		return result;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId
				+ ", notificationCreatedTime=" + notificationCreatedTime+ "]";
	}

}
