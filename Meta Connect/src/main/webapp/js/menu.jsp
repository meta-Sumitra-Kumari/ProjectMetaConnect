
<div class="col-lg-3 col-md-4 col-sm-3 col-xs-12 " >
<div class=" container-fluid well"style="min-height:100vh">
	<div class="sidebar">
		<div class="row">
			<div class="col-lg-5 col-md-4  col-sm-8 col-xs-12 ">
				<div class="visible-xs col-xs-4 "></div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-4 ">
					<figure class="thumbnail" style="height:100%">
						<img class="img-responsive" src="${user.imagePath}" />
					</figure>
				</div>
				<div class="visible-xs col-xs-4 "></div>
			</div>
			<div class="col-lg-5 col-md-6 col-sm-8 col-xs-10 text-left ">
			 <strong>${user.firstName}&nbsp;${user.lastName}</strong>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
					metacube</div>

			</div>
			<div class="btn-dropdown col-lg-2 col-md-2 col-sm-3 col-xs-2 ">
				<div class="btn-group">
					<button type="button" class="btn btn-info dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						
						<i class="glyphicon glyphicon-cog"></i>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
						<li><a href="MyPost">My Post</a></li>
						<li><a href="getMyGroup">My Group</a></li>
						<li><a href="joinagroup">Request For Group</a></li>
						<li><a href="logout">Logout</a></li>

					</ul>
				</div>
			</div>
		</div>
		<a href="createGroup">
		<div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<h3> <strong>Create Group</strong></h3>
			</div>
		</a>
	</div>
</div>
</div>