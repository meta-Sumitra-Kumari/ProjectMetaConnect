package com.metaconnect.notification.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import com.metaconnect.user.model.User;


/**
 * @author MetaConnect
 *
 */
@Entity
@Table(name = "NOTIFICATION_READ")
@Data
public class NotificationRead {
		@Id
	    @Column(name = "notification_read_id")
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int notificationReadId;
		
		 @ManyToOne
		 @JoinColumn(name = "user_id")
		    private User userId;
		 
		 @Column(name = "notification_id")
		    private int notificationId;

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NotificationRead other = (NotificationRead) obj;
			if (notificationId != other.notificationId)
				return false;
			if (notificationReadId != other.notificationReadId)
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + notificationId;
			result = prime * result + notificationReadId;
			return result;
		}

		@Override
		public String toString() {
			return "NotificationRead [notificationReadId=" + notificationReadId
					+ ", notificationId=" + notificationId + "]";
		}

		
		 
}
