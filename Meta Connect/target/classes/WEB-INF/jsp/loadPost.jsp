<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:choose>
	<c:when test="${loadmorePosts.size()!=0}">
		<c:forEach items="${loadmorePosts}" var="post">
			<%@ include file="postSnippet.jsp"%>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="col-lg-12 col-md -12 col-sm-12 col-xs-12">
			<div class="alert alert-info alert-dismissable">
				<a class="panel-close close" data-dismiss="alert">×</a> <i
					class="fa fa-coffee"></i><strong>No More Post</strong>
			</div>
		</div>
	</c:otherwise>
</c:choose>