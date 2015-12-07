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
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 postDiv">
				Search Result</div>
				<!-- edit form column -->
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 personal-info">
					<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">×</a> <i
							class="fa fa-coffee"></i><strong>${message}</strong>
					</div>
					<c:forEach items="${postlist}" var="post">
		<%@ include file = "postSnippet.jsp" %>
		
	</c:forEach>
						
				</div>
			</div>
			
	
	
	


</body>
</html>