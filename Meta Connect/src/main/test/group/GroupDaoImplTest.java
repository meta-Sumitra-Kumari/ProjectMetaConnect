package group;

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
import com.metaconnect.post.dao.PostDao;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class GroupDaoImplTest {


	private Post post = new Post();
	private Group group = new Group(); 
	private User user = new User(); 
	private GroupMember groupmember = new GroupMember();
	@Autowired
	private GroupMemberDao groupmemberDaoimpl;
	@Autowired
	private PostDao postDaoImpl;
	@Autowired
	private UserDao userDaoImpl;
	@Autowired
	private GroupDao groupDaoImpl;
	/**
	 * set user and group object data
	 * insert user object into database
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
		userDaoImpl.add(user);
		group.setGroupAdminId(user);
		group.setGroupDescription("hello");
		group.setGroupName("testtest"); 
	}
	/**
	 * set user and group object  value null
	 */
	@After
	public void tearDown() throws Exception {
		user = null;
		group = null;
	}
	/**
	 *  use to test Add function -- insert the group object into database
	 */
	@Transactional
	@Test
	public void testAdd() {
		int Expected = groupDaoImpl.getAllGroup().size() + 1;
		groupDaoImpl.add(group); 
		int Actual = groupDaoImpl.getAllGroup().size();
		assertEquals(Expected,Actual );

	}
	/**
	 *  use to test Edit function -- insert the updated group object into database
	 */
	@Transactional
	@Test
	public void testEdit() {

		groupDaoImpl.add(group);
		Group groupTemp = groupDaoImpl.getGroup(group.getGroupId());
		groupTemp.setGroupDescription("testEdit"); 
		groupDaoImpl.add(groupTemp);
		Group Expected = groupTemp;
		Group Actual = groupDaoImpl.getGroup(group.getGroupId());
		assertEquals(Expected.toString(), Actual.toString());
	}

	/**
	 *  use to test GetGroup function -- retrieve the  Group object from database by GroupId
	 */

	@Transactional
	@Test
	public void testGetGroup() {
		Group Expected = group;
		groupDaoImpl.add(group);
		Group Actual = groupDaoImpl.getGroup(group.getGroupId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 *  use to test GetAllGroup function -- retrieve the all  Group object from database
	 */
	@Transactional
	@Test
	public void testGetAllGroup() {
		int Expected = groupDaoImpl.getAllGroup().size() + 1;
		groupDaoImpl.add(group);
		int Actual =  groupDaoImpl.getAllGroup().size();
		assertEquals(Expected,Actual);
	}
	/**
	 *  use to test GetAllGroupForRequest function -- retrieve the all  Group object of user 
	 */
	@Transactional
	@Test
	public void testGetAllGroupForRequest() {
		
		User userTemp = new User();
		userDaoImpl.add(userTemp);
		int Expected = groupDaoImpl.getAllGroupForRequest(userTemp).size()+1;
		groupDaoImpl.add(group); 
		int Actual = groupDaoImpl.getAllGroupForRequest(userTemp).size();
        assertEquals(Expected, Actual);
	}
	/**
	 *  use to testgetAllPostOfGroup function -- retrieve the all  post  object of group 
	 */
	@Transactional
	@Test
	public void testgetAllPostOfGroup(){
		user.setFirstName("testFirst");
		user.setLastName("testLast");
		user.setEmail("test@metacube.com");
		user.setImagePath("/ImagePath");
		user.setPhoneNumber(123465791);
		user.setAddress("testJaipur1");
		user.setGender("male"); 
		group.setGroupAdminId(user);
		group.setGroupDescription("hello");
		group.setGroupName("testtest");  
		groupmember.setUserId(user);
		groupmember.setGroupId(group);
		groupmember.setStatus(1);
		groupmemberDaoimpl.add(groupmember);
		post.setPostDescription("hello post");
		post.setGroupId(group);
		post.setUserId(user);
		postDaoImpl.add(post);
		int Expected = groupDaoImpl.getAllPostOfGroup(group).size()+1;
		Post postNew = new Post();
		postNew.setGroupId(group);
		postNew.setUserId(user);
		postDaoImpl.add(postNew);
		int Actual = groupDaoImpl.getAllPostOfGroup(group).size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test getLastGroup function -- retrieve latest  Group object from database
	 */
	@Transactional
	@Test
	public void testgetLatestGroup(){
		group.setGroupName("latestGroup");
		Group Expected = group;
		groupDaoImpl.add(group);
		Group Actual = groupDaoImpl.getLatestGroup();
		assertEquals(Expected, Actual);
		
	}
	/**
	 *  use to test getAllGroupForRequestAccept -- retrieve all  Group object that joined by user
	 */
	@Transactional
	@Test
	public void testgetAllGroupForRequestAccept(){
		user.setFirstName("testFirst");
		user.setLastName("testLast");
		user.setEmail("test@metacube.com");
		user.setImagePath("/ImagePath");
		user.setPhoneNumber(123465791);
		user.setAddress("testJaipur1");
		user.setGender("male"); 
		group.setGroupAdminId(user);
		group.setGroupDescription("hello");
		group.setGroupName("testtest");  
		groupmember.setUserId(user);
		groupmember.setGroupId(group);
		groupmember.setStatus(1);
		groupmemberDaoimpl.add(groupmember);
		int Expected = groupDaoImpl.getAllGroupForRequestAccept(user).size();
		GroupMember groupmemberNew = new GroupMember();
		groupmemberNew.setUserId(user);
		groupmemberNew.setGroupId(group);
		groupmemberNew.setStatus(1);
		groupmemberDaoimpl.add(groupmemberNew);
		int Actual = groupDaoImpl.getAllGroupForRequestAccept(user).size();
		assertEquals(Expected, Actual);
		
	}

}
