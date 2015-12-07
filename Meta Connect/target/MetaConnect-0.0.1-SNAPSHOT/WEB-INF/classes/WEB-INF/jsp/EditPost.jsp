

<%@page import="org.apache.velocity.runtime.directive.Include"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="head.jsp"%>
<body class="container well">


	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>



	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12 mainSection">
	<c:choose>
		<c:when test="${message!=null}">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<div class="alert alert-info alert-dismissable">
			
						<a class="panel-close close" data-dismiss="alert">×</a> <i
							class="fa fa-coffee"></i><strong>${message}</strong>
					</div>
					</div>
		
		
		</c:when>
		<c:otherwise>
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 postDiv">
				Edit Post</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 formForPost">
				<form:form commandName="post" id="postForm">
					<div class="row form-group">
						<input type="hidden" path="postId" name= "id" value="${post.postId}"/>
						<input type="hidden" path="groupId" name = "groupid" value="${post.groupId}"/>
						<input type="hidden" path="userId" name = "userid" value="${post.userId}"/>
					
						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
							<form:textarea placeholder="Post Something...."
								class=" col-lg-12 col-md-12 col-sm-12 col-xs-12"
								path="postDescription" id="postDescription" rows="3" ></form:textarea>
						</div>
						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 " id = "errorPost">
							
						</div>
					</div>
					
						

						<div
							class=" col-lg-offset-4 col-lg-2 col-md-offset-4 col-md-2 col-sm-offset-3 col-sm-3  col-xs-offset-8 col-xs-4 pull-right ">
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="action"
								value="Share" class="btn btn-info" id="submitPost" onclick="updatePost(${post.postId})"
								 />
						</div>

					

				</form:form>
	</div>
	</c:otherwise>
	</c:choose>
	</div>
</body>
</html>