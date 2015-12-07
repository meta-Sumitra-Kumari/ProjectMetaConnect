package com.metaconnect.comment.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Entity
@Table(name = "COMMENTS")
@Data
public class Comments {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    
    @Column(name="comment_discription", length = 1000)
    private String commentDescription;
    
    @Column(name="comment_created_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp commentCreatedTime;
    
    @Column(name="comment_modified_time",columnDefinition = "TIMESTAMP")
    private Timestamp commentModifiedTime;
    
    @ManyToOne(cascade = {CascadeType.MERGE},fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;
    
    @ManyToOne(cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post postId;
    
    @Column(name = "comment_status",columnDefinition = "INT DEFAULT 0")
    private int commentStatus;
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comments other = (Comments) obj;
		if (commentCreatedTime == null) {
			if (other.commentCreatedTime != null)
				return false;
		} else if (!commentCreatedTime.equals(other.commentCreatedTime))
			return false;
		if (commentDescription == null) {
			if (other.commentDescription != null)
				return false;
		} else if (!commentDescription.equals(other.commentDescription))
			return false;
		if (commentId != other.commentId)
			return false;
		if (commentModifiedTime == null) {
			if (other.commentModifiedTime != null)
				return false;
		} else if (!commentModifiedTime.equals(other.commentModifiedTime))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((commentCreatedTime == null) ? 0 : commentCreatedTime
						.hashCode());
		result = prime
				* result
				+ ((commentDescription == null) ? 0 : commentDescription
						.hashCode());
		result = prime * result + (int) (commentId ^ (commentId >>> 32));
		result = prime
				* result
				+ ((commentModifiedTime == null) ? 0 : commentModifiedTime
						.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", commentDescription="
				+ commentDescription + ", commentCreatedTime="
				+ commentCreatedTime + ", commentModifiedTime="
				+ commentModifiedTime + "]";
	}

	

}
