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
			Profile</div>
			<c:choose>
<c:when test="${viewUser==null}">
	<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">×</a> <i
							class="fa fa-coffee"></i><strong>No Such a user exists</strong>
					</div>
</c:when>
<c:otherwise>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 userinfo">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<img src="${viewUser.imagePath}" width="100" height="100" />
			</div>
			<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label class="col-lg-4 col-md-4 col-sm-4 col-xs-4">First
						name:</label>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						${viewUser.firstName}</div>


				</div>


				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">Last
						name:</label>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						${viewUser.lastName}</div>

				</div>



				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">Email:</label>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

						${viewUser.email}</div>



				</div>

				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">Address:</label>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						${viewUser.address}</div>

				</div>

				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">Phone
						Number:</label>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						${viewUser.phoneNumber}</div>

				</div>



				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">Gender:</label>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						${viewUser.gender}</div>
				</div>



			</div>
		</div>
		</c:otherwise>
		</c:choose>
	</div>




</body>
</html>