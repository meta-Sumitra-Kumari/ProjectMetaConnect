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
import com.metaconnect.group.dao.impl.GroupDaoImpl;
import com.metaconnect.group.model.Group;
import com.metaconnect.group.service.GroupService;
import com.metaconnect.groupmembers.dao.GroupMemberDao;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.post.dao.PostDao;
import com.metaconnect.post.dao.impl.PostDaoImpl;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.dao.impl.UserDaoImpl;
import com.metaconnect.user.model.User;
/**
 * @author MetaConnect
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class GroupServiceImplTest extends Object {

	
	private Group group = new Group(); 
	private User user = new User();
	private Post post = new Post();
	private GroupMember groupmember = new GroupMember();
	@Autowired
	private PostDao postDaoImpl = new PostDaoImpl();
	@Autowired
	private GroupDaoImpl groupDaoImpl;
	@Autowired
	private GroupMemberDao groupmemberDaoimpl;
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private GroupService groupServiceImpl;
	/**
	 * set user and group object data
	 * insert user object into database
	 */
	@Before
	public void setUp() throws Exception {
		user.setUserId(1); 
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
		int Expected = groupServiceImpl.getAllGroup().size() + 1;
		groupServiceImpl.add(group); 
		int Actual = groupServiceImpl.getAllGroup().size();
		assertEquals(Expected,Actual );

	}
	/**
	 *  use to test Edit function -- insert the updated group object into database
	 */
	@Transactional
	@Test
	public void testEdit() { 
		groupServiceImpl.add(group);
		Group groupTemp = groupServiceImpl.getGroup(group.getGroupId());
		groupTemp.setGroupDescription("testEdit"); 
		groupServiceImpl.add(groupTemp);
		Group Expected = groupTemp;
		Group Actual = groupServiceImpl.getGroup(group.getGroupId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 *  use to test GetGroup function -- retrieve the  Group object from database by GroupId
	 */
	@Transactional
	@Test
	public void testGetGroup() {

		Group Expected = group;
		groupServiceImpl.add(group);
		Group Actual = groupServiceImpl.getGroup(group.getGroupId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 *  use to test GetAllGroup function -- retrieve the all  Group object from database
	 */
	@Transactional
	@Test
	public void testGetAllGroup() {
		int Expected = groupServiceImpl.getAllGroup().size() + 1;
		groupServiceImpl.add(group);
		int Actual =  groupServiceImpl.getAllGroup().size();
		assertEquals(Expected,Actual);
	}
	/**
	 *  use to test GetMyGroup function -- retrieve the all  Group object of user from database
	 */
	@Transactional
	@Test
	public void testGetMyGroup() {
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
		int Expected = groupServiceImpl.getMyGroup(user).size()+1; 
		Group groupNew =  new Group();
		groupNew.setGroupAdminId(user);
		groupNew.setGroupDescription("hello");
		groupNew.setGroupName("testtest");  
		GroupMember groupmemberNew = new GroupMember();
		groupmemberNew.setUserId(user);
		groupmemberNew.setGroupId(groupNew);
		groupmemberNew.setStatus(1);
		groupmemberDaoimpl.add(groupmemberNew);
		int Actual = groupServiceImpl.getMyGroup(user).size();
		assertEquals(Expected,Actual ); 
		
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
		int Expected = groupServiceImpl.getAllPostOfGroup(group).size()+1;
		Post postNew = new Post();
		postNew.setGroupId(group);
		postNew.setUserId(user);
		postDaoImpl.add(postNew);
		int Actual = groupServiceImpl.getAllPostOfGroup(group).size();
		assertEquals(Expected,Actual ); 
	}
	/**
	 *  use to test getLastGroup function -- retrieve latest  Group object from database
	 */
	@Transactional
	@Test
	public void testgetLatestGroup(){
		group.setGroupName("latestGroup");
		Group Expected = group;
		groupServiceImpl.add(group);
		Group Actual = groupServiceImpl.getLatestGroup();
		assertEquals(Expected, Actual);
		
	}
}
