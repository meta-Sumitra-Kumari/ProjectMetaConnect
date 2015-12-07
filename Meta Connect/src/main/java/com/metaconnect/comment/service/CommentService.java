package com.metaconnect.comment.service;

import java.util.List;

import com.metaconnect.comment.model.Comments;
import com.metaconnect.post.model.Post;
import com.metaconnect.user.model.User;

/**
 * @author MetaConnect
 *
 */
public interface CommentService {
	
	public void edit(Comments comment);
	public Comments getComment(long commentId);
	public List getAllComment();
	public void add(Comments comment, User user, Post post);
	public Comments getLastCOmment();
}
