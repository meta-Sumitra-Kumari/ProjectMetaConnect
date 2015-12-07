package notification;

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
import com.metaconnect.groupmembers.dao.GroupMemberDao;
import com.metaconnect.groupmembers.model.GroupMember;
import com.metaconnect.notification.dao.NotificationDao;
import com.metaconnect.notification.dao.NotificationReadDao;
import com.metaconnect.notification.dao.impl.NotificationReadDaoImpl;
import com.metaconnect.notification.model.Notification;
import com.metaconnect.notification.model.NotificationRead;
import com.metaconnect.post.dao.PostDao;
import com.metaconnect.post.dao.impl.PostDaoImpl;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.dao.impl.UserDaoImpl;
import com.metaconnect.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml" })
public class NotificationReadDaoImplTest {
	private Post post = new Post();
	private User user = new User();
	private Group group = new Group();
	private GroupMember groupmember = new GroupMember();
	private NotificationRead notificationRead = new NotificationRead();
	@Autowired
	private GroupMemberDao groupmemberDaoimpl;
	private Notification notification = new Notification();
	@Autowired
	private PostDao postDaoImpl = new PostDaoImpl();
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private GroupDaoImpl groupDaoImpl;
	@Autowired
	private NotificationDao notificationDaoImpl;
	@Autowired
	private NotificationReadDao notificationReadDaoImpl;
	@Before
	public void setUp() {
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
		GroupMember groupmemberNew = new GroupMember();
		groupmemberNew.setUserId(user);
		groupmemberNew.setGroupId(group);
		groupmemberNew.setStatus(1);
		groupmemberDaoimpl.add(groupmemberNew);
		
		post.setPostDescription("hello post");
		post.setGroupId(group);
		post.setUserId(user);
		postDaoImpl.add(post);
	}

	@After
	public void tearDown() {
		user = null;
		group = null;
		post = null;
	}

	@Transactional
	@Test
	public void testAdd() {
		notification.setGroupId(group);
		notification.setPostId(post);
		notificationDaoImpl.add(notification); 
		notificationRead.setUserId(user);
		notificationRead.setNotificationId(notification.getNotificationId());  
		notificationReadDaoImpl.add(notificationRead); 
		 
	}

	
}
