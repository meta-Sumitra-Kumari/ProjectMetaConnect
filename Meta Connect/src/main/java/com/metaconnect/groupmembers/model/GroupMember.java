package com.metaconnect.groupmembers.model;

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

import com.metaconnect.group.model.Group;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Entity
@Table(name = "GROUP_MEMBER")
@Data
public class GroupMember {

    @Id
	@Column(name="group_member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long groupMemberId;
    
    @ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User userId;
        
    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "group_id")       
	private Group groupId;
	
	
	@Column(name="group_joined_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp groupJoinedTime;
	
	@Column(name="status",columnDefinition = "INT DEFAULT 0")
	private int status;
	
	@Column(name="seen",columnDefinition = "BIT(1) DEFAULT 0")
	private Boolean seen;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupMember other = (GroupMember) obj;
		if (groupJoinedTime == null) {
			if (other.groupJoinedTime != null)
				return false;
		} else if (!groupJoinedTime.equals(other.groupJoinedTime))
			return false;
		if (groupMemberId != other.groupMemberId)
			return false;
		if (seen == null) {
			if (other.seen != null)
				return false;
		} else if (!seen.equals(other.seen))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((groupJoinedTime == null) ? 0 : groupJoinedTime.hashCode());
		result = prime * result
				+ (int) (groupMemberId ^ (groupMemberId >>> 32));
		result = prime * result + ((seen == null) ? 0 : seen.hashCode());
		result = prime * result + status;
		return result;
	}

	@Override
	public String toString() {
		return "GroupMember [groupMemberId=" + groupMemberId
				+ ", groupJoinedTime=" + groupJoinedTime + ", status=" + status
				+ ", seen=" + seen + "]";
	}



	
	}
