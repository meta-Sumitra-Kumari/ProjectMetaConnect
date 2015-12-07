package user;

import static org.junit.Assert.assertEquals;
import java.util.Set;
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
import com.metaconnect.post.dao.PostDao;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
/**
 * @author Subhash
 *
 */
/**
 * @author Subhash
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class UserDaoImplTest {

	private Post post = new Post();
	private Group group = new Group();
	private User user = new User();
	@Autowired
	private UserDao userDaoImpl;
	@Autowired
	private GroupDao groupDaoImpl;
	@Autowired
	private PostDao postDaoImpl;

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
		User Actual = userDaoImpl.add(user);
		assertEquals(Expected.toString(), Actual.toString());

	}

	/**
	 * use to test EditUser function -- edit data of user into database
	 */
	@Transactional
	@Test
	public void testEditUser() {
		User userTemp = userDaoImpl.add(user);
		userTemp.setFirstName("NewTestFirst");
		User Expected = userTemp;
		userDaoImpl.edit(userTemp);
		User Actual = userDaoImpl.getUser(userTemp.getUserId());
		assertEquals(Expected.toString(), Actual.toString());

	}


	/**
	 * use to test GetUser function -- function return the object of user by userId as a parameter
	 */
	@Transactional
	@Test
	public void testGetUser() {
		User Expected = user;
		User userTemp = userDaoImpl.add(user);
		User Actual = userDaoImpl.getUser(userTemp.getUserId());
		assertEquals(Expected.toString(), Actual.toString());
	}

	/**
	 * use to test GetUserByEmail function -- function return the object of user according to user email
	 */
	@Transactional
	@Test
	public void testGetUserByEmail() {
		User Expected = user;
		User userTemp = userDaoImpl.add(user);
		User Actual = userDaoImpl.getUserByEmail(userTemp);
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
	 * use to test testgetAllPostsOfUser function -- function return all the post of user objects
	 */
	@Transactional
	@Test
	public void testgetAllPostsOfUser() {

		User userTemp = userDaoImpl.add(user);
		group.setGroupAdminId(userTemp);
		group.setGroupDescription("hello");
		group.setGroupName("testtest");
		groupDaoImpl.add(group);
		post.setPostDescription("hello post");
		post.setGroupId(group);
		post.setUserId(userTemp);
		postDaoImpl.add(post);
		Set<Post> postSet = userDaoImpl.getAllPostsOfUser(user);
		int Expected = postSet.size() + 1;
		Post postNew = new Post();
		postNew.setPostDescription("hello post new");
		postNew.setGroupId(group);
		postNew.setUserId(userTemp);
		postDaoImpl.add(postNew);
		int Actual = userDaoImpl.getAllPostsOfUser(user).size();
		assertEquals(Expected, Actual);
	}

	@After
	public void tearDown() {
		user = null;
		group = null;
		post = null;
	}
}
