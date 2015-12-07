package comment;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.comment.service.CommentService;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class CommentServiceImplTest {
	
	private Post post = new Post();
	private User user = new User();
	private Group group = new Group();
	private Comments comment = new Comments();
	@Autowired
	private UserDao userDaoImpl;
	@Autowired
	private PostDao postDaoImpl ;
	@Autowired
	private GroupDao groupDaoImpl;
	@Autowired
	private CommentService commentServiceImpl ;
	/**
	 * use to set set user ,post ,group and comment object data 
	 * insert the user ,group and post object into database
	 * @throws Exception
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
		post.setPostDescription("hello post");
		post.setGroupId(group);
		post.setUserId(user);
		postDaoImpl.add(post);
		comment.setCommentDescription("commentDescription");
		comment.setPostId(post);
		comment.setUserId(user);
	}
	/**
	 * use to set set user ,post ,group and comment object value null
	 */
	@After
	public void tearDown() throws Exception {
		user = null;
		group = null;
		post = null;
		comment = null;

	}
	/**
	 *  use to test Add function -- insert the comment object into database
	 */
	@Transactional
	@Test
	public void testAdd() {
		int Expected = commentServiceImpl.getAllComment().size() + 1;
		commentServiceImpl.add(comment, user, post);
		int Actual =commentServiceImpl.getAllComment().size();
		assertEquals(Expected, Actual);
	}
	/**
	 *  use to test Edit function -- insert the updated comment object into database
	 */
	@Transactional
	@Test
	public void testEdit() {
		comment.setCommentId(11);
		commentServiceImpl.add(comment, user, post);
		Comments commentTemp = commentServiceImpl.getComment(comment.getCommentId());
		commentTemp.setCommentDescription("testEdit");
		String Expected  = "testEdit";
		commentServiceImpl.add(comment, user, post);
		Comments commentNew = commentServiceImpl.getComment(comment.getCommentId());
		String Actual = commentNew.getCommentDescription();
		assertEquals(Expected , Actual);
	}
	/**
	 *  use to test getLastComment function -- retrieve latest  comment object from database
	 */
	@Transactional
	@Test
	public void testgetLastCOmment() { 
		Comments Expected= comment;
		commentServiceImpl.add(comment,user,post);
		Comments  Actual =commentServiceImpl.getLastCOmment();
		assertEquals(Expected.toString(),Actual.toString());

	}
	/**
	 *  use to test GetComment function -- retrieve the  comment object from database by commentId
	 */
	@Transactional
	@Test
	public void testGetComment() {
		Comments Expected= comment;
		comment.setCommentId(11);
		commentServiceImpl.add(comment,user,post);
		Comments  Actual =commentServiceImpl.getComment(comment.getCommentId());
		assertEquals(Expected.toString(),Actual.toString());
	}
	/**
	 *  use to test GetAllComment function -- retrieve the all  comment object from database
	 */
	@Transactional
	@Test
	public void testGetAllComment() {
		int Expected = commentServiceImpl.getAllComment().size() + 1;
		commentServiceImpl.add(comment, user, post);
		int Actual =commentServiceImpl.getAllComment().size();
		assertEquals(Expected, Actual); 

	}

}
