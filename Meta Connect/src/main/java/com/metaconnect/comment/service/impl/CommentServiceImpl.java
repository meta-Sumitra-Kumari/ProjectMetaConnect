package com.metaconnect.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metaconnect.comment.dao.CommentDao;
import com.metaconnect.comment.model.Comments;
import com.metaconnect.comment.service.CommentService;
import com.metaconnect.post.model.Post;
import com.metaconnect.post.service.PostService;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private PostService postService;

	/* (non-Javadoc)
	 * will call commentDao method to add the comment
	 * @see com.metaconnect.comment.service.CommentService#add(com.metaconnect.comment.model.Comments, com.metaconnect.user.model.User, com.metaconnect.post.model.Post)
	 */
	@Transactional
	public void add(Comments comment, User user, Post post) {
		comment.setPostId(post);
		comment.setUserId(user);
		commentDao.add(comment);

	}

	/* (non-Javadoc)
	 * will call commentDao method to edit the comment
	 * @see com.metaconnect.comment.service.CommentService#edit(com.metaconnect.comment.model.Comments)
	 */
	@Transactional
	public void edit(Comments comment) {
		commentDao.edit(comment);
	}

	/* (non-Javadoc)
	 * will call commentDao method to get Comment by id
	 * @see com.metaconnect.comment.service.CommentService#getComment(long)
	 */
	@Transactional
	public Comments getComment(long commentId) {
		return commentDao.getComment(commentId);
	}

	/* (non-Javadoc)
	 * will call commentDao method to get All comments
	 * @see com.metaconnect.comment.service.CommentService#getAllComment()
	 */
	@Transactional
	public List getAllComment() {
		return commentDao.getAllComment();
	}

	/* (non-Javadoc)
	 * will call commentDao method to get lastComment
	 * @see com.metaconnect.comment.service.CommentService#getLastCOmment()
	 */
	@Transactional
	public Comments getLastCOmment() {
		return commentDao.getLastComment();
	}

}
