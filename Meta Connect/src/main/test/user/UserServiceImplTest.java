package user;

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
import com.metaconnect.user.service.UserService;

/**
 * @author MetaConnect
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class UserServiceImplTest {
	private User user = new User();
	private Group group = new Group(); 
	private User userTemp;
	private GroupMember groupmember = new GroupMember();
	@Autowired
	private UserDao userDaoImpl;
	@Autowired
	private GroupDao groupDaoImpl; 
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private GroupMemberDao groupmemberDaoimpl;

	/**
	 * use to set the user data into user object
	 */
	@Before
	public void setUp() {
		user.setFirstName("testFirstName");
		user.setLastName("testLastName");
		user.setEmail("test@metacube.com");
		user.setImagePath("/ImagePath");
		user.setPhoneNumber(123456791);
		user.setAddress("testJaipur");
		user.setGender("male");
	}
	/**
	 * use to test addUser function -- that function insert user data  into database 
	 */
	@Transactional
	@Test
	public void testAddUser() {
		User Expected = user;
		User Actual = userServiceImpl.add(user);
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 * use to test EditUser function -- edit data of user into database
	 */
	@Transactional
	@Test
	public void testEditUser() {
		User userTemp = userServiceImpl.add(user);
		userTemp.setFirstName("NewTestFirst");
		User Expected = userTemp;
		userServiceImpl.edit(userTemp);
		User Actual = userServiceImpl.getUser(userTemp.getUserId());
		assertEquals(Expected.toString(), Actual.toString());

	}
 

	/**
	 * use to test GetUser function -- function return the object of user by userId as a parameter
	 */
	@Transactional
	@Test
	public void testGetUser() {
		User Expected = user;
		User userTemp = userServiceImpl.add(user);
		User Actual = userServiceImpl.getUser(userTemp.getUserId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 * use to test getAllUsers function -- function return all user objects
	 */
	@Transactional
	@Test
	public void testgetAllUsers() {

		int Expected = userDaoImpl.getAllUser().size() + 1;
		userDaoImpl.add(user);
		int Actual = userDaoImpl.getAllUser().size();
		assertEquals(Expected, Actual);
	}

	/**
	 * use to test getMyGroup -- return all the group of user 
	 */
	@Transactional
	@Test
	public void testgetMyGroup() {
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
		groupmember.setStatus(1);
		int Expected = userServiceImpl.getMyGroup(user).size() + 1;
		groupmemberDaoimpl.add(groupmember);
		int Actual = userServiceImpl.getMyGroup(user).size();
		assertEquals(Expected, Actual);
	}

	/**
	 * use to test getAllRequest function -- return all the join group request of user
	 */
	@Transactional
	@Test
	public void testgetAllRequest() {
		group.setGroupAdminId(user);
		group.setGroupDescription("hello");
		group.setGroupName("testtest");
		groupDaoImpl.add(group);
		groupmember.setUserId(userTemp);
		groupmember.setGroupId(group);
		groupmemberDaoimpl.add(groupmember);
		long Expected = userServiceImpl.getAllRequest(user).size() + 1;
		GroupMember groupmemberNew = new GroupMember();
		groupmemberNew.setUserId(user);
		groupmemberNew.setGroupId(group);
		groupmemberDaoimpl.add(groupmemberNew);
		long Actual = userServiceImpl.getAllRequest(user).size();
		assertEquals(Expected, Actual);
	}

	@After
	public void tearDown() {
		user = null;
		group = null;
	}
}
