<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<%@include file="head.jsp"%>
</head>
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
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 newPostDiv">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 postDiv">
				Create New Group</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 formForPost">
		   <form:form action="updateGroup?groupid=${group.groupId}" id="createGroupForm" commandName="group" method="POST" onsubmit="return validate()" enctype="multipart/form-data" >
		
			<spring:bind path="groupName">
			<div class="row form-group ${status.error ? 'has-error' : ''}">
				<div class=" col-lg-4 col-md-4 col-sm-6 col-xs-12 "><label>Enter Group Name *</label></div>
				<div class=" col-lg-8 col-md-8 col-sm-6 col-xs-12 ">
							<form:input placeholder="write group name...."
								class=" col-lg-12 col-md-12 col-sm-12 col-xs-12"
								path="groupName" id="groupName" rows="3"></form:input>
						</div>	
				<div class=col-lg-4></div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12"><form:errors path="groupName" class="control-label" /></div>
			</div>
			</spring:bind>
			<spring:bind path="groupDescription">
			<div class="row form-group ${status.error ? 'has-error' : ''}">
				<div class=" col-lg-4 col-md-4 col-sm-6  col-xs-12"><label>Group Description	*</label></div>
				<div class=" col-lg-8 col-md-8 col-sm-6 col-xs-12 ">
							<form:textarea placeholder="write group Description...."
								class=" col-lg-12 col-md-12 col-sm-12 col-xs-12"
								path="groupDescription" id="groupDescription" rows="3"></form:textarea>
						</div>
				<div class=col-lg-4></div>
				<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12"><form:errors path="groupDescription" class="control-label" /></div>
			</div>
			</spring:bind>
			
																	
				
			
			
			
			<div class="row form-group">
			
				<div class=" col-lg-offset-4 col-lg-4 col-md-12 col-sm-12  col-xs-12 " colspan="2"><input type="submit" class ="btn btn-success" value="save changes" id="cretategroup" >
		
				</div>
			</div>
			
	</form:form>
	</div>
	</div>
	
		</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>