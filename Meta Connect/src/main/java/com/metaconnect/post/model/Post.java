package com.metaconnect.post.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.group.model.Group;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Entity
@Table(name = "POST")
@Data
public class Post {

	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long postId;

	@Column(name = "post_description", length= 1000)
	private String postDescription;

	@Column(name = "post_created_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp postCreatedTime;

	@Column(name = "post_modified_time", columnDefinition = "TIMESTAMP")
	private Timestamp postModifiedTime;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User userId;

	@ManyToOne(cascade = {CascadeType.ALL, CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name = "group_id")
	private Group groupId;

	@OneToMany(mappedBy = "postId",fetch=FetchType.EAGER)
	private Set<Comments> postsComments;

	@OneToMany(mappedBy = "postId",fetch=FetchType.EAGER)
	private Set<Notification> postNotification;
	
	 @Column(name = "post_status",columnDefinition = "INT DEFAULT 0")
	 private int postStatus;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (postCreatedTime == null) {
			if (other.postCreatedTime != null)
				return false;
		} else if (!postCreatedTime.equals(other.postCreatedTime))
			return false;
		if (postDescription == null) {
			if (other.postDescription != null)
				return false;
		} else if (!postDescription.equals(other.postDescription))
			return false;
		if (postId != other.postId)
			return false;
		if (postModifiedTime == null) {
			if (other.postModifiedTime != null)
				return false;
		} else if (!postModifiedTime.equals(other.postModifiedTime))
			return false;
		
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((postCreatedTime == null) ? 0 : postCreatedTime.hashCode());
		result = prime * result
				+ ((postDescription == null) ? 0 : postDescription.hashCode());
		result = prime * result + (int) (postId ^ (postId >>> 32));
		result = prime
				* result
				+ ((postModifiedTime == null) ? 0 : postModifiedTime.hashCode());
		
		return result;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postDescription="
				+ postDescription + ", postCreatedTime=" + postCreatedTime
				+ ", postModifiedTime=" + postModifiedTime + ", postsComments="
				+ postsComments + ", postNotification=" + postNotification
				+ "]";
	}

	


}
