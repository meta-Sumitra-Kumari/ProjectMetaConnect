package post;

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
import com.metaconnect.group.dao.impl.GroupDaoImpl;
import com.metaconnect.group.model.Group;
import com.metaconnect.post.model.Post;
import com.metaconnect.post.service.PostService;
import com.metaconnect.user.dao.UserDao;
import com.metaconnect.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class PostServiceImplTest {

	private Post post = new Post();
	private User user = new User();
	private Group group = new Group();
	@Autowired
	private UserDao userDaoImpl;
	@Autowired
	private GroupDaoImpl groupDaoImpl;
	@Autowired
	private PostService postServiceImpl;
	/**
	 * use to set user ,group and post object data 
	 */
	@Before
	public void setUp() {

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
		post.setPostDescription("hello post");
		post.setGroupId(group);
		post.setUserId(user);
	}

	@After
	public void tearDown() throws Exception {
		user = null;
		group = null;
		post = null;
	}
	/**
	 * use to test ADD function -- that post insert into database
	 */
	@Transactional
	@Test
	public void testAdd() {
		int Expected = postServiceImpl.getAllPost().size() + 1;
		postServiceImpl.add(post);
		int Actual = postServiceImpl.getAllPost().size();
		assertEquals(Expected, Actual);
	}
	
	/**
	 * use to test Edit function --  that insert updated post object  into database
	 */
	@Transactional
	@Test
	public void testEdit() {

		postServiceImpl.add(post);
		Post postTemp = postServiceImpl.getPost(post.getPostId());
		Post Expected = postTemp;
		postTemp.setPostDescription("testEditPostDesc");
		postServiceImpl.add(postTemp);
		Post Actual = postServiceImpl.getPost(post.getPostId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	
	/**
	 * use to test GetPost function --  get Post object from database by postId
	 */
	@Transactional
	@Test
	public void testGetPost() {
		Post Expected = post;
		postServiceImpl.add(post);
		Post Actual = postServiceImpl.getPost(post.getPostId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 * use to test GetAllPost function --  that retrieve all the post from database
	 */
	@Transactional
	@Test
	public void testGetAllPost() {
		int Expected = postServiceImpl.getAllPost().size() + 1;
		postServiceImpl.add(post);
		int Actual = postServiceImpl.getAllPost().size();
		assertEquals(Expected, Actual);
	}
	/**
	 * use to test GetAllPostOfUser function --  that retrieve all post of user
	 */
	@Transactional
	@Test
	public void testGetAllPostOfUser() {

		User userTemp = userDaoImpl.add(user);
		group.setGroupAdminId(userTemp);
		group.setGroupDescription("hello");
		group.setGroupName("testtest");
		groupDaoImpl.add(group);
		post.setPostDescription("hello post");
		post.setGroupId(group);
		post.setUserId(userTemp);
		postServiceImpl.add(post);
		Set<Post> postSet = postServiceImpl.getAllPostOfUser(user);
		int Expected = postSet.size() + 1;
		Post postNew = new Post();
		postNew.setPostDescription("hello post new");
		postNew.setGroupId(group);
		postNew.setUserId(userTemp);
		postServiceImpl.add(postNew);
		int Actual = postServiceImpl.getAllPostOfUser(user).size();
		assertEquals(Expected, Actual);

	}
	/**
	 * use to test getLatestPost function --  that retrieve latest post from database
	 */
	@Transactional
	@Test
	public void testgetLatestPost() {
		post.setPostDescription("TestGetLatestPostFunction");
		Post Expected = post;
		postServiceImpl.add(post);
		Post Actual = postServiceImpl.getLatestPost();
		assertEquals(Expected.toString(), Actual.toString());

	}

	@Transactional
	@Test
	public void getAllPostOnHome() {

	}
}
