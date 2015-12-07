<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 sideMenu">
	
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 nameDiv">
				Hi!&nbsp;${user.firstName}&nbsp;${user.lastName}
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
					
				<img class="thumbnail img-responsive" src="${user.imagePath}">
			</div>
					
		</div>
		
		<div class="row text-left sideMenuLink"  >
			<ul>
				<li><a href="viewProfile?userid=${user.userId}"><span class="glyphicon glyphicon-user"></span> View Profile</a></li>
				<li><a href="editUser"><span class="glyphicon glyphicon-pencil"></span> Edit Profile</a></li>
				<li><a href="getMyGroup"> <span class="fa fa-users"></span> My Groups</a></li>
				<li><a href="joinagroup"> <i class="fa fa-users-plus"></i> Join Group</a></li>
					<li><a href="createGroup"> <span class="glyphicon glyphicon-plus"></span> Create new Group</a></li>
				<li><a href="MyPost"><span class="glyphicon glyphicon-th-list"></span> My Post</a></li>
			
			</ul>
		</div>
		
		</div>