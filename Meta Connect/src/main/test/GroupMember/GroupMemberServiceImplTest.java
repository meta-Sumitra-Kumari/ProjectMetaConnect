package GroupMember;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.metaconnect.group.dao.GroupDao;
import com.metaconnect.group.model.Group;
import com.metaconnect.groupmembers.dao.GroupMemberDao;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.groupmembers.service.GroupMemberService;
import com.metaconnect.user.dao.impl.UserDaoImpl;
import com.metaconnect.user.model.User;
/**
 * @author MetaConnect
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class GroupMemberServiceImplTest {
	
	private Group group = new Group(); 
	private User user = new User(); 
	private GroupMember groupmember = new GroupMember();
	@Autowired
	private GroupMemberService groupmemberServiceimpl;
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private GroupDao groupDaoImpl;
	@Autowired
	private GroupMemberDao groupmemberDaoimpl;
	/**
	 * set user and group object data
	 * insert user,group object into database
	 */
	@Before
	public void setUp() throws Exception {
		user.setFirstName("testFirst");
		user.setLastName("testLast");
		user.setEmail("test@metacube.com");
		user.setImagePath("/ImagePath");
		user.setPhoneNumber(123);
		user.setAddress("testJaipur1");
		user.setGender("male");
		userDaoImpl.add(user);
		group.setGroupAdminId(user);
		group.setGroupDescription("hello");
		group.setGroupName("testtest"); 
		groupDaoImpl.add(group);
		groupmember.setUserId(user);
		groupmember.setGroupId(group);
	}
	/**
	 * set user,groupmember  and group object  value null
	 */
	@After
	public void tearDown() throws Exception {
		user = null;
		group = null;
		groupmember = null;
	}
	/**
	 *  use to test Add function -- insert the groupmember object into database
	 */
	@Transactional
	@Test
	public void testAdd() {
		int Expected = groupmemberServiceimpl.getAllGroupMember().size() + 1;
		groupmemberServiceimpl.add(groupmember);
		int Actual = groupmemberServiceimpl.getAllGroupMember().size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test getAllGroupMember function -- retrieve the all  GroupMember object from database
	 */
	@Transactional
	@Test
	public void testGetAllGroupMember() {
		int Expected = groupmemberServiceimpl.getAllGroupMember().size() + 1;
		groupmemberServiceimpl.add(groupmember);
		int Actual = groupmemberServiceimpl.getAllGroupMember().size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test addGroupAdmin function -- add groupmember into database  
	 */
	@Transactional
	@Test
	public void testAddGroupAdmin() {
		int size = groupmemberServiceimpl.getAllGroupMember().size() + 1;
		groupmemberServiceimpl.addGroupAdmin(group, user);
		assertEquals(size, groupmemberServiceimpl.getAllGroupMember().size());
	}
	/**
	 *  use to test edit function -- insert the updated groupmember object into database
	 */
	@Transactional
	@Test
	public void testedit(){ 
		groupmemberServiceimpl.add(groupmember);
		GroupMember groupmemberNew = groupmemberServiceimpl.getGroupMemberforRequest(groupmember.getGroupMemberId());
		groupmemberNew.setStatus(1);
		groupmemberServiceimpl.edit(groupmemberNew);
		GroupMember groupmemberTest =groupmemberServiceimpl.getGroupMemberforRequest(groupmemberNew.getGroupMemberId());
		assertEquals(1, groupmemberTest.getStatus());
	}
	/**
	 *  use to test getGroupMember function -- retrieve groupmember object by group object
	 */
	@Transactional
	@Test
	public void testgetGroupMember(){
		int size = groupmemberServiceimpl.getGroupMember(group).size()+1;
		groupmember.setStatus(1);
		groupmemberServiceimpl.add(groupmember);
		assertEquals(size, groupmemberServiceimpl.getGroupMember(group).size());	
	} 
	/**
	 *  use to test getAllRequestedGroup function -- retrieve all group  that user can join
	 */
	@Transactional
	@Test
	public void testgetAllRequestedGroup(){ 
		User userTemp = new User();
		userDaoImpl.add(userTemp);
		groupmemberDaoimpl.add(groupmember);
		long Expected = groupmemberDaoimpl.getAllRequestedGroup(userTemp)
				.size() + 1;
		GroupMember groupmemberNew = new GroupMember();
		groupmemberNew.setUserId(user);
		groupmemberNew.setGroupId(group);
		groupmemberDaoimpl.add(groupmemberNew);
		long Actual = groupmemberDaoimpl.getAllRequestedGroup(userTemp).size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test getGroupMemberforRequest function -- retrieve groupmember object by the groupmemberId
	 */
	@Transactional
	@Test
	public void testgetGroupMemberforRequest(){
		groupmemberServiceimpl.add(groupmember);
		GroupMember Expected = groupmember;
		GroupMember Actual = groupmemberServiceimpl
				.getGroupMemberforRequest(groupmember.getGroupMemberId());
		assertEquals(Expected.toString(), Actual.toString());
		 
	}

}
