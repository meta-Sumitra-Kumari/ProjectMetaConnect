package com.metaconnect.comment.dao;

import java.util.List;

import com.metaconnect.comment.model.Comments;

/**
 * @author MetaConnect
 *
 */
public interface CommentDao {
	public void add(Comments comment);

	public void edit(Comments comment);

	public Comments getComment(long commentId);

	public List<Comments> getAllComment();

	public Comments getLastComment();
}
