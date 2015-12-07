

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
		
			

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 groupinfo">



				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<h2>${group.groupName}</h2>
				</div>
				<div class="col-lg-push-5 col-lg-3 col-md-4 col-sm-4 col-xs-4">
					
					<button type="button" class ="btn btn-info " name="action" data-groupid="${group.groupId}"
						id="submitButton">Join</button>
				
				</div>
			</div>


			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-1">
				<h3>${group.groupAdminId.firstName}</h3>
			</div>
				
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<h3 class="groupDescription">Group description</h3>
				${group.groupDescription}


			</div>
		</div>
</body>
</html>






