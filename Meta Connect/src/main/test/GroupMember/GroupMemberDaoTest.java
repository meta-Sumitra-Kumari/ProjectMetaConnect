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
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;
/**
 * @author MetaConnect
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class GroupMemberDaoTest {
	private Group group = new Group();
	private User user = new User();
	private GroupMember groupmember = new GroupMember();
	@Autowired
	private UserDao userDaoImpl;
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
		user.setPhoneNumber(123465791);
		user.setAddress("testJaipur1");
		user.setGender("male");
		User userTemp = userDaoImpl.add(user);
		group.setGroupAdminId(userTemp);
		group.setGroupDescription("hello");
		group.setGroupName("testtest");
		groupDaoImpl.add(group);
		groupmember.setUserId(userTemp);
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
		int Expected = groupmemberDaoimpl.getAllGroupMember().size() + 1;
		groupmemberDaoimpl.add(groupmember);
		int Actual = groupmemberDaoimpl.getAllGroupMember().size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test getAllGroupMember function -- retrieve the all  GroupMember object from database
	 */
	@Transactional
	@Test
	public void testgetAllGroupMember() {
		int Expected = groupmemberDaoimpl.getAllGroupMember().size() + 1;
		groupmemberDaoimpl.add(groupmember);
		int Actual = groupmemberDaoimpl.getAllGroupMember().size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test getAllJoinedGroup function -- retrieve the all  group object joined by user
	 */
	@Transactional
	@Test
	public void testgetAllJoinedGroup() {
		groupmember.setStatus(1);
		int Expected = groupmemberDaoimpl.getAllJoinedGroup(user).size() + 1;
		groupmemberDaoimpl.add(groupmember);
		int Actual = groupmemberDaoimpl.getAllJoinedGroup(user).size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test getAllRequestedGroup function -- retrieve the all  group object that user able to join
	 */
	@Transactional
	@Test
	public void testgetAllRequestedGroup() {
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
	 *  use to test getAllRequest function -- retrieve the all  group request for admin of group
	 */
	@Transactional
	@Test
	public void testgetAllRequest() {
		groupmemberDaoimpl.add(groupmember);
		long Expected = groupmemberDaoimpl.getAllRequest(user).size() + 1;
		GroupMember groupmemberNew = new GroupMember();
		groupmemberNew.setUserId(user);
		groupmemberNew.setGroupId(group);
		groupmemberDaoimpl.add(groupmemberNew);
		long Actual = groupmemberDaoimpl.getAllRequest(user).size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test getGroupMemberforRequest function -- retrieve groupmember object by the groupmemberId
	 */
	@Transactional
	@Test
	public void testgetGroupMemberforRequest() {
		groupmemberDaoimpl.add(groupmember);
		GroupMember Expected = groupmember;
		GroupMember Actual = groupmemberDaoimpl
				.getGroupMemberforRequest(groupmember.getGroupMemberId());
		assertEquals(Expected.toString(), Actual.toString());
	}
}
