<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 postSection">
	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 userImage">
		<img class="img-responsive thumbnail" src="${post.userId.imagePath}" />
	</div>

	<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 post">



		<div class="h5">
			<a href="viewProfile?userid=${post.userId.userId}" class="fontchange">${post.userId.firstName}&nbsp;${post.userId.lastName}</a>
			&nbsp;&nbsp;posted on <a href="groupPageForUserInGroup?groupid=${post.groupId.groupId}" class="fontchange">${post.groupId.groupName}</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${post.postModifiedTime}
		</div>



		<div class="post-div col-lg-12 col-md-12 col-sm-12 col-xs-12">

			<div class="row postDiscription">
				<span id="postDescription_${post.postId}">${post.postDescription}</span>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 buttons">
			<c:choose>
				<c:when test="${user.userId == post.userId.userId}">
					<a href="updatePost?postid=${post.postId}">Edit</a>
					<a href="deletePost?postid=${post.postId}">Delete</a>
				</c:when>
			</c:choose>
		</div>
	</div>

	<div id="comments_${post.postId}" name="hello comment${post.postId}"
		data-u="${post.postId}">

		<c:forEach items="${post.postsComments}" var="comment">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
				<c:choose>
					<c:when test="${comment.commentStatus==0}">
						<%@ include file="commentSnippet.jsp"%>
					</c:when>
				</c:choose>
			</div>
		</c:forEach>

	</div>
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
		<form:form action="" commandName="comment"
			id="commentform_${post.postId}">
			<input type="hidden" name="id" value="${post.postId}" />
			<div class="row form-group">
				<div class="col-lg-push-2 col-lg-10 col-md-push-2 row">
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<img class="img-responsive thumbnail" src="${user.imagePath}" />
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
						<form:textarea class="col-lg-12 col-md-12 col-sm-12  col-xs-12 " onfocus="removeErrorBox(${post.postId})"
							placeholder="write your comment.." id="commentDescription_${post.postId}"
							path="commentDescription" rows="2" />
					</div>
	<div class=" col-lg-push-2 col-lg-10 col-md-12 col-sm-12 col-xs-12"
					id="errorcomment_${post.postId}"></div>
				</div>
				<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12"
					id="errorcomment_${post.postId}"></div>
				<div
					class=" col-lg-offset-10 col-lg-2 col-md-offset-4 col-md-2 col-sm-offset-3 col-sm-3  col-xs-offset-8 col-xs-4 pull-right  ">
					<input type="button" class="btn btn-info btn-sm" name="action"
						data-postId="${post.postId}" value="Comment" id="submitComment"
						onclick="addComment(${post.postId})">
				</div>
			</div>




		</form:form>
	</div>
</div>