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
		<!-- edit form column -->
		<div class="col-md-9 personal-info">
			<div class="alert alert-info alert-dismissable">
				<a class="panel-close close" data-dismiss="alert">×</a> <i
					class="fa fa-coffee"></i> <strong>Please Update your
					profile </strong>

			</div>
			<h3>Personal info</h3>
			<form:form method="POST" commandName="user"
				id="registrationForm">
				<spring:bind path="firstName">
					<div class="form-group row ${status.error ? 'has-error' : ''}">
						<label class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">First name:</label>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
							<form:input class="form-control" type="text" path="firstName"
								id="firstName" name="firstName" required="true" />
						</div>
						<div class="col-lg-3 col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" id= "fNameError">
							<form:errors path="firstName" class="control-label" />
						</div>

					</div>
				</spring:bind>
				<spring:bind path="lastName">

					<div class="form-group row ${status.error ? 'has-error' : ''}">
						<label class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">Last name:</label>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
							<form:input class="form-control" type="text" path="lastName"
								id="lastName" name="lastName" required="true" />
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" id= "lNameError">
							<form:errors path="lastName" class="control-label" />
						</div>
					</div>
				</spring:bind>
				<spring:bind path="email">

					<div class="form-group row ${status.error ? 'has-error' : ''}">
						<label class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">Email:</label>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">

							<form:input class="form-control" type="text" path="email"
								id="email" name="email" disabled="true" required="true" />
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
							<form:errors path="email" class="control-label" />
						</div>


					</div>
				</spring:bind>
				<spring:bind path="address">
					<div class="form-group row ${status.error ? 'has-error' : ''}">
						<label class="col-lg-3 col-md-3 col-sm-3 col-xs-3">Address:</label>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
							<form:input class="form-control" type="text" id="address"
								path="address" required="true"></form:input>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" id= "addressError">
							<form:errors path="address" class="control-label" />
						</div>
					</div>
				</spring:bind>
				<spring:bind path="phoneNumber">

					<div class="form-group row${status.error ? 'has-error' : ''}">
						<label class="col-lg-3 col-md-3 col-sm-3 col-xs-3">Phone Number:</label>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
							<form:input class="form-control" type="text" id="phoneNumber"
								path="phoneNumber" required="true"></form:input>
						</div>
						<div class="col-lg-3 col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9" id= "phoneNumberError">
							<form:errors path="phoneNumber" class="control-label" />
						</div>
					</div>
				</spring:bind>
				<spring:bind path="gender">

					<div class="form-group row ${status.error ? 'has-error' : ''}">
						<label class="col-lg-3 col-md-3 col-sm-3 col-xs-3">Gender:</label>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
							<span class="controls span2"> <form:select id="select01"
									path="gender" style="width: 100%;">
									<form:option value="male">Male</form:option>
									<form:option value="female">Female</form:option>
								</form:select>
							</span>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3"></div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
							<form:errors path="gender" class="control-label" />
						</div>
					</div>
				</spring:bind>

				<div class="form-group row">
					<label class="col-lg-3 col-md-3 col-sm-3 col-xs-3  control-label"></label>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<input type="button" class="btn btn-primary" value="Save Changes" id="submitProfile">
							<span></span>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<input type="reset" class="btn btn-danger" value="Cancel">
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>






</body>
</html>
