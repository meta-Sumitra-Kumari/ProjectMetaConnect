

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="head.jsp"%>
<body class = "container well">


	<%@include file="header.jsp"%>
	<%@include file="menu.jsp"%>
	
	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12 mainSection">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 myPost">
				My Notification</div>	
		
		<c:choose>
			<c:when test="${notificationList==null || notificationList.size()==0}">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<div class="alert alert-info alert-dismissable">
			
						<a class="panel-close close" data-dismiss="alert">×</a> <i
							class="fa fa-coffee"></i><strong>You have no notification to view</strong>
					</div>
					</div>
					</c:when>
					<c:otherwise>
	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12 mainSection">
		<c:forEach items="${notificationList}" var="notification">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
		 <div class="notification-div well">
						<a href = "post?notificationid=${notification.notificationId}">
						<span>${notification.postId.userId.firstName}&nbsp;${requests.postId.userId.lastName}&nbsp;posted on &nbsp;${notification.groupId.groupName}
						</span>
						</a>
					</div>
					
		</div>
		
	</c:forEach>

	</div>
	</c:otherwise>
	</c:choose>
	</div>
	
</body>
</html>


