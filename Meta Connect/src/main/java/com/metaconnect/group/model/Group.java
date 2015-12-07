package com.metaconnect.group.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;


/**
 * @author MetaConnect
 *
 */
@Entity
@Table(name = "GROUPS")
@Data
public class Group {
	
	@Id
	@Column(name="group_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long groupId;
	
	@Column(name="group_name")
	private String groupName;
	
	@Column(name="group_description")
     private String groupDescription;



	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "group_admin_id")
	private User groupAdminId;

	@Column(name="group_created_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp groupCreatedTime;

	@Column(name="group_modified_time",columnDefinition = "TIMESTAMP")
	private Timestamp groupModifiedTime;
	
	@OneToMany(mappedBy="groupId",fetch=FetchType.EAGER)
	private Set<GroupMember> groupMembers;
	
	@OneToMany(mappedBy="groupId",fetch=FetchType.EAGER)
	private Set<Post> groupPosts;
	
	@OneToMany(mappedBy="groupId",fetch=FetchType.EAGER)
	private Set<Notification> groupNotification;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (groupCreatedTime == null) {
			if (other.groupCreatedTime != null)
				return false;
		} else if (!groupCreatedTime.equals(other.groupCreatedTime))
			return false;
		if (groupDescription == null) {
			if (other.groupDescription != null)
				return false;
		} else if (!groupDescription.equals(other.groupDescription))
			return false;
		if (groupId != other.groupId)
			return false;
		if (groupModifiedTime == null) {
			if (other.groupModifiedTime != null)
				return false;
		} else if (!groupModifiedTime.equals(other.groupModifiedTime))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((groupCreatedTime == null) ? 0 : groupCreatedTime.hashCode());
		result = prime
				* result
				+ ((groupDescription == null) ? 0 : groupDescription.hashCode());
		result = prime * result + (int) (groupId ^ (groupId >>> 32));
		result = prime
				* result
				+ ((groupModifiedTime == null) ? 0 : groupModifiedTime
						.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		
		return result;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName
				+ ", groupDescription=" + groupDescription + ", groupCreatedTime=" + groupCreatedTime
				+ ", groupModifiedTime=" + groupModifiedTime + "]";
	}

	
}
