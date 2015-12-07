package com.metaconnect.comment.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metaconnect.comment.dao.CommentDao;
import com.metaconnect.comment.model.Comments;


/**
 * @author MetaConnect
 *
 */
@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory session;

	/* (non-Javadoc)
	 * will add the comment to the comment to datbase
	 * @see com.metaconnect.comment.dao.CommentDao#add(com.metaconnect.comment.model.Comments)
	 */
	@Override
	public void add(Comments comment) {
		session.getCurrentSession().save(comment);

	}


	/* (non-Javadoc)
	 * will edit the comment in the database
	 * @see com.metaconnect.comment.dao.CommentDao#edit(com.metaconnect.comment.model.Comments)
	 */
	@Override
	public void edit(Comments comment) {
		session.getCurrentSession().merge(comment);

	}


	/* (non-Javadoc)
	 * will get the comment from the comments table with commentId
	 * @see com.metaconnect.comment.dao.CommentDao#getComment(long)
	 */
	@Override
	public Comments getComment(long commentId) {

		return (Comments) session.getCurrentSession().get(Comments.class,
				commentId);
	}


	/* (non-Javadoc)
	 * @see com.metaconnect.comment.dao.CommentDao#getAllComment()
	 * will return the list of all the comments whose's status is 0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comments> getAllComment() {

		return session.getCurrentSession()
				.createQuery("from Comments where comment_status = 0").list();
	}

	/* (non-Javadoc)
	 * @see com.metaconnect.comment.dao.CommentDao#getLastComment()
	 * will return the last Comment.
	 */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Comments getLastComment() {
		List<Comments> comments = session.getCurrentSession()
				.createQuery("from Comments order by commentId DESC LIMIT 1")
				.list();

		if (comments.size() != 0) {
			return comments.get(0);
		}
		return null;
	}

}
