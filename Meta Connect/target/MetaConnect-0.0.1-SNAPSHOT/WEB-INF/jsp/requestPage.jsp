

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
				My Request</div>	
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<c:choose>
<c:when test="${request==null || request.size()==0}">
	<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">×</a> <i
							class="fa fa-coffee"></i><strong>You have no request to view</strong>
					</div>
</c:when>
<c:otherwise>					
		<c:forEach items="${request}" var="requests">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
		 <div class="request-div   well   ">
						<span>${requests.userId.firstName}&nbsp;${requests.userId.lastName}&nbsp;requested for &nbsp;${requests.groupId.groupName}</span>
					</div>
					
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
		
		  <div class="form-group">
						<div class="row ">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-primary">
								<input type="Submit" name = "action" value="accept" data-id="${requests.groupMemberId}" id="accept" />
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<input type="Submit" name = "action" value="deny" data-id="${requests.groupMemberId}" id="deny" />
							</div>
						</div>
					</div>
		
		</div>
	</c:forEach>
</c:otherwise>
</c:choose>
	</div>
	</div>
</body>
</html>


