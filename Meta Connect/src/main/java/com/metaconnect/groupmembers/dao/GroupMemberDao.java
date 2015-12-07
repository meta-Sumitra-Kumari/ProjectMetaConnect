package com.metaconnect.groupmembers.dao;



import java.util.List;
import java.util.Set;

import com.metaconnect.group.model.Group;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface GroupMemberDao {
	public void add(GroupMember groupMember);
	public void edit(GroupMember groupMember);
	public Set<GroupMember> getGroupMember(Group group);
	public List<GroupMember> getAllGroupMember();
	public Set<GroupMember> getAllJoinedGroup(User user);
	public Set<GroupMember> getAllRequestedGroup(User user);
	public Set<GroupMember> getAllRequest(User user);
	public GroupMember getGroupMemberforRequest(long groupMemberId);
}
