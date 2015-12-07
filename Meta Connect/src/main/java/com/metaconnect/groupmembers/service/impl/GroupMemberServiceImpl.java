package com.metaconnect.groupmembers.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.group.model.Group;
import com.metaconnect.groupmembers.dao.GroupMemberDao;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.groupmembers.service.GroupMemberService;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Service
public class GroupMemberServiceImpl implements GroupMemberService {

	@Autowired
	private GroupMemberDao groupMemberDao;
	
	/* (non-Javadoc)
	 * this method will call groupMemberDao to add the groupMember
	 * @see com.metaconnect.groupmembers.service.GroupMemberService#add(com.metaconnect.groupmembers.model.GroupMember)
	 */
	@Transactional
	public void add(GroupMember groupMember) {
		groupMemberDao.add(groupMember);
	}
	
	/* (non-Javadoc)
	 * this method will call groupMemberDao to edit the groupMember
	 * @see com.metaconnect.groupmembers.service.GroupMemberService#edit(com.metaconnect.groupmembers.model.GroupMember)
	 */
	@Transactional
	public void edit(GroupMember groupMember) {
		groupMemberDao.edit(groupMember);

	}

	/* (non-Javadoc)
	 * this method will call groupMemberDao to get All the member of specified group
	 * @see com.metaconnect.groupmembers.service.GroupMemberService#getGroupMember(com.metaconnect.group.model.Group)
	 */
	@Transactional
	public Set<GroupMember> getGroupMember(Group group) {
		
		return groupMemberDao.getGroupMember(group);
	}

	/* (non-Javadoc)
	 * this will return all the groupMembers
	 * @see com.metaconnect.groupmembers.service.GroupMemberService#getAllGroupMember()
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<GroupMember> getAllGroupMember() {
		return groupMemberDao.getAllGroupMember();
	}

	/* (non-Javadoc)
	 * this method is used to add the group admin in the group member table
	 * this will set the groupId in groupMember table from groupMember
	 * will set groupMemberUserId to the id of user who created the group
	 * will set the status 1 
	 * will set seen true
	 * and will add the entry to table
	 * @see com.metaconnect.groupmembers.service.GroupMemberService#addGroupAdmin(com.metaconnect.group.model.Group, com.metaconnect.user.model.User)
	 */
	@Override
	public void addGroupAdmin(Group group, User user) {
		GroupMember groupMember=new GroupMember();
		groupMember.setGroupId(group);
		groupMember.setUserId(user);
		groupMember.setSeen(true);
		groupMember.setStatus(1);
		groupMemberDao.add(groupMember);
	}
	
	/* (non-Javadoc)
	 * will send the request of all the group for which user can request
	 * @see com.metaconnect.groupmembers.service.GroupMemberService#getAllRequestedGroup(com.metaconnect.user.model.User)
	 */
	@Override
	public Set<Group> getAllRequestedGroup(User user){
		
		Set<GroupMember> groupMembers=groupMemberDao.getAllRequestedGroup(user);
		Set<Group> groups = new LinkedHashSet<Group>();
		if(groupMembers!=null){
		for (GroupMember groupmember : groupMembers) {
			groups.add(groupmember.getGroupId());
		}
		}
		return groups;
	}

	/* (non-Javadoc)
	 * will return entry of the groupMember who has requested.
	 * @see com.metaconnect.groupmembers.service.GroupMemberService#getGroupMemberforRequest(long)
	 */
	@Override
	public GroupMember getGroupMemberforRequest(long groupMemberId) {
		return groupMemberDao.getGroupMemberforRequest(groupMemberId);
	}

}
