

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

		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 newPostDiv">

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 postDiv">
				What's New</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 formForPost">
				<form:form action="" commandName="newPost" id="postForm">
					<div class="row form-group">

						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
							<form:textarea placeholder="Post Something...."
								class=" col-lg-12 col-md-12 col-sm-12 col-xs-12"
								path="postDescription" id="postDescription"  htmlEscape="true" rows="3" style="overflow:hidden"></form:textarea>
						</div>
						<%-- <input type="hidden" name="target" value="<%=sanitizedTarget%>"> --%>
						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 " id = "errorPost">
							
						</div>
					</div>
					<div class="row form-group" id = "selectgroup" style="display:none;">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<label>Select Group&nbsp;&nbsp;&nbsp;</label> <select
								id="groupid" name="groupid">
								<c:forEach items="${allgroups}" var="group1">
									<option value="${group1.groupId}">${group1.groupName}&nbsp;&nbsp;</option>
								</c:forEach>
							</select>
						</div>

						<div
							class=" col-lg-offset-4 col-lg-2 col-md-offset-4 col-md-2 col-sm-offset-3 col-sm-3  col-xs-offset-8 col-xs-4 pull-right ">
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="action"
								value="Share" class="btn btn-info" id="submitPost"
								onclick="addPost(${group1.groupId})" />
						</div>

					</div>

				</form:form>
			</div>

		</div>
		<div id="newPost">
			<c:forEach items="${allPosts}" var="post">
			<%@ include file = "postSnippet.jsp" %>
				
			</c:forEach>
			
		</div>
		<div id ="loadMorePostOnHome "class=" col-lg-12 col-md-12  col-sm-12 col-xs-12 text-center ">
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="action"
								value="LoadMore" class="btn btn-info " id="LoadMorePost" />
						</div>
	</div>
</body>
</html>


