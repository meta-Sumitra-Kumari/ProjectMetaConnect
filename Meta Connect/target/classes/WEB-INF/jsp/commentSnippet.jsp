
<div class="col-lg-push-2 col-lg-10 col-md-push-2 row">
	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
		<img class="img-responsive thumbnail"
			src="${comment.userId.imagePath}" />
	</div>

	<div class="col-lg-10 col-md-8 col-sm-8 col-xs-8">
		<div class="col-lg-12 col-md-9 col-sm-9 col-xs-9">
			<a href="viewProfile?userid=${comment.userId.userId}" class="fontchange">${comment.userId.firstName}&nbsp;${comment.userId.lastName}</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${comment.commentModifiedTime}

		</div>

		<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<span>${comment.commentDescription}</span>
		</div>

		<div
			class="col-lg-push-4 col-lg-8 col-md-12 col-sm-12 col-xs-12 buttons">
			<c:choose>
				<c:when test="${user.userId == comment.userId.userId}">
					<a href="updateComment?commentid=${comment.commentId}">edit</a>
					<a href="deleteComment?commentid=${comment.commentId}">delete</a>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>