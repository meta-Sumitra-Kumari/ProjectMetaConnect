package com.metaconnect.groupmembers.dao.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.group.dao.GroupDao;
import com.metaconnect.group.model.Group;
import com.metaconnect.groupmembers.dao.GroupMemberDao;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Transactional
@Repository
public class GroupMemberDaoImpl implements GroupMemberDao {

	@Autowired
	private SessionFactory session;

	@Autowired
	private GroupDao groupDao;

	/* (non-Javadoc)
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#add(com.metaconnect.groupmembers.model.GroupMember)
	 * will save the groupMember entry to the database 
	 */
	@Override
	public void add(GroupMember groupMember) {
		session.getCurrentSession().save(groupMember);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#edit(com.metaconnect.groupmembers.model.GroupMember)
	 * will edit the groupMember entry in database.
	 */
	@Override
	public void edit(GroupMember groupMember) {
		session.getCurrentSession().merge(groupMember);
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#getGroupMember(com.metaconnect.group.model.Group)
	 * will return the list of all the  groupMembers of the requested group those's user's which are active
	 */
	@Override
	public Set<GroupMember> getGroupMember(Group group) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				GroupMember.class);
		newUsers.add(Restrictions.eq("groupId", group));
		newUsers.add(Restrictions.eq("status", 1));
		@SuppressWarnings("unchecked")
		Set<GroupMember> groupMembers = new LinkedHashSet<GroupMember>(newUsers.list());
		return groupMembers;
	}

	/* (non-Javadoc)
	 * will return all the groupMembers
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#getAllGroupMember()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GroupMember> getAllGroupMember() {
		return session.getCurrentSession().createQuery("from GroupMember")
				.list();
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#getAllJoinedGroup(com.metaconnect.user.model.User)
	 * Will return the list of all the groups which user has joined 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<GroupMember> getAllJoinedGroup(User user) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				GroupMember.class);
		newUsers.add(Restrictions.eq("userId", user));
		newUsers.add(Restrictions.eq("status", 1));
		Set<GroupMember> group = new LinkedHashSet<GroupMember>(newUsers.list());
		return group;

	}

	/* (non-Javadoc)
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#getAllRequestedGroup(com.metaconnect.user.model.User)
	 * will return the list of those group in which user is not added and can request.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<GroupMember> getAllRequestedGroup(User user) {
		Criteria newUsers = session.getCurrentSession().createCriteria(
				GroupMember.class);
		Set<Group> groups = groupDao.getAllGroupForRequest(user);
		Set<GroupMember> group =null;
		if(groups!=null && groups.size()!=0)
		{	
		newUsers.add(Restrictions.in("groupId", groups));
		newUsers.add(Restrictions.not(Restrictions.eq("userId",user)));
	    Criteria u1 = session.getCurrentSession().createCriteria(
				GroupMember.class);
		u1.add(Restrictions.in("groupId", groups));
		u1.add(Restrictions.eq("userId",user));
		Criterion status = Restrictions.eq("status", 0);
		Criterion status2 = Restrictions.eq("status", -1);
		LogicalExpression orExp = Restrictions.or(status,status2);
		u1.add(orExp);
		u1.setProjection(Projections.property("groupId"));
		List<Group> result1 = u1.list();
		if(result1.size()!=0)
		{
		newUsers.add(Restrictions.not(Restrictions.in("groupId",result1)));
		}
		 group = new HashSet<GroupMember>(newUsers.list());
		}
		return group;

	}
	
	/* (non-Javadoc)
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#getAllRequest(com.metaconnect.user.model.User)
	 * will send the list of request for the requested group to the group admin
	 */
	@SuppressWarnings("unchecked")
	@ Override
	public Set<GroupMember> getAllRequest(User user)
	{Set<GroupMember> groupMembers=null;
		Set<Group> groups=groupDao.getAllGroupForRequestAccept(user);
		Criteria newUsers = session.getCurrentSession().createCriteria(
				GroupMember.class);
		if(groups.size()!=0)
		{
		newUsers.add(Restrictions.in("groupId", groups));
		newUsers.add(Restrictions.eq("status",0));
		groupMembers=new LinkedHashSet<GroupMember>(newUsers.list());
		}
		return groupMembers;
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.groupmembers.dao.GroupMemberDao#getGroupMemberforRequest(long)
	 */
	@Override
	public GroupMember getGroupMemberforRequest(long groupMemberId) {
		return (GroupMember) session.getCurrentSession().get(GroupMember.class, groupMemberId);
	}

}
