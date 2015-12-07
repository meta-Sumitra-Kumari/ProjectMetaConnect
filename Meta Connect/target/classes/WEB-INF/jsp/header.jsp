<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar navbar-inverse " role="navigation">
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target="#myNavbar">
		<span class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="#">MetaConnect</a>
</div>
<div class="collapse navbar-collapse" id="myNavbar">
	<ul class="nav navbar-nav">

		<li><a href="Home">Home</a></li>
		<li><a href="showNotification ">Notification<span
				class="badge">${notificationList.size()}</span></a></li>
		<li><a href="requestAccept ">Request<span class="badge">${request.size()}</span></a></li>
		<li><a href="logout">Sign out</a></li>
	</ul>

	<ul class="nav navbar-nav navbar-right">
		<li><form class="navbar-form" action="search"
				id="searchForm" method="POST" onsubmit="return validate()">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search Post"
						name="search" />

					<div class="input-group-btn">
						<button class="form-control btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>

				</div>
			</form></li>
	</ul>
</div>

</nav>