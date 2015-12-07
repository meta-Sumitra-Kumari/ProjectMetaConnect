package com.metaconnect.groupmembers.service;

import java.util.List;



import java.util.Set;

import com.metaconnect.group.model.Group;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface GroupMemberService {
	public void add(GroupMember groupMember);
	public void edit(GroupMember groupMember);
	public Set<GroupMember> getGroupMember(Group group);
	public List<GroupMember> getAllGroupMember();
	public void addGroupAdmin(Group group, User user);
	public Set<Group> getAllRequestedGroup(User user);
	public GroupMember getGroupMemberforRequest(long groupMemberId);
}
