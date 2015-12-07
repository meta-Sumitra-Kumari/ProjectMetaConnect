package post;

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
public class PostDaoImplTest {
	private Post post = new Post();
	private User user = new User();
	private Group group = new Group();
	@Autowired
	private PostDao postDaoImpl = new PostDaoImpl();
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private GroupDaoImpl groupDaoImpl;

	/**
	 * use to set user ,group and post object data 
	 */
	@Before
	public void setUp() {
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
		post.setPostDescription("hello post");
		post.setGroupId(group);
		post.setUserId(userTemp);
	}

	@After
	public void tearDown() {
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
		int Expected = postDaoImpl.getAllPost().size() + 1;
		postDaoImpl.add(post);
		int Actual = postDaoImpl.getAllPost().size();
		assertEquals(Expected, Actual);
	}
	/**
	 * use to test Delete function -- that post delete from database
	 */
	@Transactional
	@Test
	public void testDelete() {
		postDaoImpl.add(post);
		int Expected = postDaoImpl.getAllPost().size() - 1;
		postDaoImpl.delete(post.getPostId());
		int Actual = postDaoImpl.getAllPost().size();
		assertEquals(Expected, Actual);

	}

	/**
	 * use to test GetPost function --  get Post object from database by postId
	 */
	@Transactional
	@Test
	public void testGetPost() {
		Post Expected = post;
		postDaoImpl.add(post);
		Post Actual = postDaoImpl.getPost(post.getPostId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 * use to test Edit function --  that insert updated post object  into database
	 */
	@Transactional
	@Test
	public void testEdit() {
		postDaoImpl.add(post);
		Post postTemp = postDaoImpl.getPost(post.getPostId());
		Post Expected = postTemp;
		postTemp.setPostDescription("testEditPostDesc");
		postDaoImpl.add(postTemp);
		Post Actual = postDaoImpl.getPost(post.getPostId());
		assertEquals(Expected.toString(), Actual.toString());
	}
	/**
	 * use to test GetAllPost function --  that retrieve all the post from database
	 */
	@Transactional
	@Test
	public void testGetAllPost() {
		int Expected = postDaoImpl.getAllPost().size() + 1;
		postDaoImpl.add(post);
		int Actual = postDaoImpl.getAllPost().size();
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
		postDaoImpl.add(post);
		Post Actual = postDaoImpl.getLatestPost();
		assertEquals(Expected.toString(), Actual.toString());
	}
	 
	@Transactional
	@Test
	public void testgetAllCommentOfUser() {

	}

	@Transactional
	@Test
	public void testgetAllPostOnHome() {

	}

}
