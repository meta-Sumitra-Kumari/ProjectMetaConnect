

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="com.metaconnect.user.model.User"
    pageEncoding="ISO-8859-1" 
    %>
    <%@ include file = "/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="head.jsp"%>
<body class="conatiner well">


	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	
	

			

<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12 mainSection">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 newPostDiv">

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 postDiv">
				What's New</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 formForPost">
				<form:form action="" commandName="newPost" id="postFromGroupPage">
					<div class="row form-group">

						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
							<form:textarea placeholder="Post Something...."
								class=" col-lg-12 col-md-12 col-sm-12 col-xs-12"
								path="postDescription" id="postDescription" rows="3"></form:textarea>
						</div>
					</div>
					<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 error" id = "errorPost">
							
						</div>
					<input type="hidden" name="groupid" id="groupid" value="${group.groupId}" />
					<div class="row form-group" style="display:none;" id = "selectgroup">
						
						<div
							class=" col-lg-offset-4 col-lg-2 col-md-offset-4 col-md-2 col-sm-offset-3 col-sm-3  col-xs-offset-8 col-xs-4 pull-right ">
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="action"
								value="Share" class="btn btn-info" id="submitPost"
								onclick="addPostFromGroupPage(${group.groupId})" />
						</div>

					</div>

				</form:form>
			</div>

		</div>
			

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 groupinfo">



				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<h2>${group.groupName}</h2>
				</div>
				<div class="col-lg-push-5 col-lg-3 col-md-4 col-sm-4 col-xs-4">
					<c:choose>
					<c:when test="${group.groupAdminId.userId == user.userId }">
					<button type="button" name="action"
						><a href="editGroup?groupid=${group.groupId}">Edit</a></button>
						</c:when>
				</c:choose>
				</div>
			</div>


			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-1">
				<h3>${group.groupAdminId.firstName}</h3>
			</div>
				
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<h3 class="groupDescription">Group description</h3>
				${group.groupDescription}


			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			
				<a href ="groupMembers?groupid=${group.groupId}">Group Members</a>


			</div>
		
		
		
	</div>
	 <div id="newPost">
	 
		<c:forEach items="${postOfGroup}" var="post">
			<%@ include file = "postSnippet.jsp" %>
	</c:forEach>
	</div>
</div>
	
</body>
</html>





		
