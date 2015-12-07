
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="head.jsp"%>
<body class="container well">
	<%@include file="header.jsp"%>

	<%@include file="menu.jsp"%>

		<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12 mainSection">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 postDiv">
				My Group</div>
			<c:choose>
			<c:when test="${mygroups.size()==0}">
	<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">×</a> <i
							class="fa fa-coffee"></i><strong>You are not include in any group</strong>
					</div>
</c:when>
<c:otherwise>	
				
				
		<c:forEach items="${mygroups}" var="group">
			<div class="col-lg-offset-1 col-lg-4 col-md-push-1 col-md-4 col-sm-push-1 col-sm-4 col-xs-12 well group-detail">
				
				<div class="col-lg-5 col-md-8 col-sm-8 col-xs-12">
								
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<a href="groupPageForUserInGroup?groupid=${group.groupId}">${group.groupName}</a></div>
								
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<h6 class="text-muted time">${group.groupAdminId.firstName}&nbsp;${group.groupAdminId.lastName}</h6>
								</div>
							</div>
		</div>
		</c:forEach>
				
			</c:otherwise>
			</c:choose>
				
	</div>
	
</body>




</html>

