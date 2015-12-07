package com.metaconnect.user.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.post.model.Post;


/**
 * @author MetaConnect
 *
 */
@Entity
@Table(name = "USER")
@Data
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@Column(name="phone_number")
	private int phoneNumber;
	
	@Column(name="address")
	private String address;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="created_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdTime;
	
	@Column(name="modified_time",columnDefinition = "TIMESTAMP")
	private Timestamp modifyTime;
		
	public long getUserId() {
	    return userId;
	}

	public void setUserId(long userId) {
	    this.userId = userId;
	}

	public String getFirstName() {
	    return firstName;
	}

	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}

	public String getLastName() {
	    return lastName;
	}

	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getImagePath() {
	    return imagePath;
	}

	public void setImagePath(String imagePath) {
	    this.imagePath = imagePath;
	}

	public int getPhoneNumber() {
	    return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
	    this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
	    return address;
	}

	public void setAddress(String address) {
	    this.address = address;
	}

	public String getGender() {
	    return gender;
	}

	public void setGender(String gender) {
	    this.gender = gender;
	}

	public Timestamp getCreatedTime() {
	    return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
	    this.createdTime = createdTime;
	}

	public Timestamp getModifyTime() {
	    return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
	    this.modifyTime = modifyTime;
	}

	public Set<GroupMember> getGroupMembers() {
	    return groupMembers;
	}

	public void setGroupMembers(Set<GroupMember> groupMembers) {
	    this.groupMembers = groupMembers;
	}

	public Set<Post> getUserPosts() {
	    return userPosts;
	}

	public void setUserPosts(Set<Post> userPosts) {
	    this.userPosts = userPosts;
	}

	public Set<Comments> getUserComments() {
	    return userComments;
	}

	public void setUserComments(Set<Comments> userComments) {
	    this.userComments = userComments;
	}

	

	@OneToMany(mappedBy="userId",fetch=FetchType.EAGER)
	private Set<GroupMember> groupMembers;
	
	@OneToMany(mappedBy="userId",fetch=FetchType.EAGER)
	private Set<Post> userPosts;
	
	@OneToMany(mappedBy="userId",fetch=FetchType.EAGER)
	private Set<Comments> userComments;
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (groupMembers == null) {
			if (other.groupMembers != null)
				return false;
		} else if (!groupMembers.equals(other.groupMembers))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (modifyTime == null) {
			if (other.modifyTime != null)
				return false;
		} else if (!modifyTime.equals(other.modifyTime))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (userComments == null) {
			if (other.userComments != null)
				return false;
		} else if (!userComments.equals(other.userComments))
			return false;
		if (userId != other.userId)
			return false;
		if (userPosts == null) {
			if (other.userPosts != null)
				return false;
		} else if (!userPosts.equals(other.userPosts))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((groupMembers == null) ? 0 : groupMembers.hashCode());
		result = prime * result
				+ ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((modifyTime == null) ? 0 : modifyTime.hashCode());
		result = prime * result + phoneNumber;
		result = prime * result
				+ ((userComments == null) ? 0 : userComments.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result
				+ ((userPosts == null) ? 0 : userPosts.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", imagePath=" + imagePath + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", gender=" + gender
				+ ", createdTime=" + createdTime + ", modifyTime=" + modifyTime
				+ ", groupMembers=" + groupMembers + ", userPosts=" + userPosts
				+ ", userComments=" + userComments + ", userNotification="
				+"]";
	}

	
	
}
