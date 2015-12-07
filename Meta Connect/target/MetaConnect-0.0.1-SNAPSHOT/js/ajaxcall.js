$(document).ready(function(){
	
	$('#LoadMorePost').click(function() 
	{
		$('#loadMorePostOnHome').innerHTML="";
		$.ajax({
			method : "GET",
			url : "PostLoading",
		}).done(function(msg) {
			$('#newPost').append(msg);
		});
	});

	$('#postDescription').focus(function show()
	{	
		$('#selectgroup').show();
		$('#errorPost').html("");
	});

	$('#submitProfile').click(function()
	{
		var checkName = /^[a-zA-Z]*$/;
		var checkNumber =/^\d+$/;
		var flag = 0;
		var fname = $("#firstName").val();
		var lname = $("#lastName").val();
		var add = $("#address").val();
		var pNum = $("#phoneNumber").val();
		var fNError = $("#fNameError");
		var lNError = $("#lNameError");
		var addError = $("#addressError");
		var pNError = $("#phoneNumberError");
		if(fname.trim() == "")
		{
			$("#firstName").addClass("red");
			flag = 1;
			fNError.text("Please specify first name");

		}
		else if(!fname.match(checkName))
		{
			$("#firstName").addClass("red");
			flag = 1;
			fNError.text("First name contains only characters");
		}
		if(lname.trim() == "")
		{
			$("#lastName").addClass("red");
			flag = 1;
			lNError.text("Please specify last name");
		}
		else if(!lname.match(checkName))
		{
			$("#lastName").addClass("red");
			flag = 1;
			lNError.text("last name contains only characters");
		}

		if(add.trim() == "")
		{
			$("#address").addClass("red");
			flag = 1;
			addError.text("Please specify address");
		}
		else if(add.length >200)
		{
			$("#address").addClass("red");
			flag = 1;
			addError.text("Address exceeds limit 200");
		}

		if(pNum.trim() == "")
		{
			$("#phoneNumber").addClass("red");
			flag = 1;
			pNError.text("Please specify phone number");
		}
		else if(!pNum.match(checkNumber))
		{
			$("#phoneNumber").addClass("red");

			flag = 1;
			pNError.text("Phone number contains only numbers");
		}
		else if(pNum.length != 10)
		{
			$("#phoneNumber").addClass("red");
			flag = 1;
			pNError.text("Phone number contains 10 digit");
		}

		if( flag == 0)
		{
			var action = "editUser";
			$("#registrationForm").attr("action", action);
			$('#registrationForm').submit();
		}
	});
	
	$("#firstName").focus(function (){
		$("#firstName").removeClass("red");
		$('#fNameError').html("");
	});
	
	$("#lastName").focus(function (){
		$("#lastName").removeClass("red");
		$('#lNameError').html("");
	});
	
	$("#address").focus(function (){
		$("#address").removeClass("red");
		$('#addressError').html("");
	});
	
	$("#phoneNumber").focus(function (){
		$("#phoneNumber").removeClass("red");
		$('#phoneNumberError').html("");
	});

	$("#creategroup").click(function(){
	
		var flag = 0;
		var checkName = /^[a-zA-Z]*$/;
		var gName = $('#groupName').val();
		var gDescription = $('#groupDescription').val().trim();
		if(gName.trim() == "")
		{
			flag = 1;
			$("#groupName").addClass("red");
			$("#groupNameError").html("Please specify group name");
		}
		else if(!gName.match(checkName))
		{
			flag = 1;
			$("#groupName").addClass("red");
			$("#groupNameError").html("Group name contains only characters");
		}

		if(gDescription.trim() == "")
		{
			flag = 1;
			$("#groupDescription").addClass("red");
			$("#groupDescriptionError").html("Please specify group description");
		}
		else if(gDescription.length > 500)
		{
			flag = 1;
			$("#groupDescription").addClass("red");
			$("#groupDescriptionError").html("Group description exceeds limit 500");

		}
		if( flag == 0)
		{
			var action="createGroup";
			$("#createGroupForm").attr("action", action);
			$('#createGroupForm').submit();
		}

	});

	$("#groupName").focus(function (){
		$("#groupName").removeClass("red");
		$('#groupNameError').html("");
	});
	
	$("#groupDescription").focus(function (){
		$("#groupDescription").removeClass("red");
		$('#groupDescriptionError').html("");
	});
	
	$('#submitButton').click(function(){
		
		var groupId = $(this).data().groupid; 
		$.ajax({
			method: "POST",
			url: "addMember",
			data: {id: groupId }
		})
		.done(function( msg ) {
			$('#submitButton').html("Request sent");
			$('#submitButton').prop('disabled', true);
		});
	});	


	$('#accept').click(function(){
		var id = $(this).data().id; 
		
		$.ajax({
			method: "POST",
			url: "acceptRequestForGroup",
			data: {id: id }
		})
		.done(function( msg ) {
		});
	});	


	$('#deny').click(function(){
		var id = $(this).data().id; 
		$.ajax({
			method: "POST",
			url: "deniedRequest",
			data: {id: id }
		})
		.done(function( msg ) {
		});
	});	


});

function removeErrorBox(id)
{
	$('#commentDescription_'+id).removeClass("red");
	$("#errorcomment_"+id).hide();
}

function addComment(id){
	$("#errorcomment_"+id).show();
	var flag =0;
	

	var comment=$('#commentDescription_'+id).val().trim();
	var cError =$("#errorcomment_"+id);
	
	if(comment == "")
	{
		$('#commentDescription_'+id).addClass("red");
		flag =1;
		cError.text("Comment is empty Write something...");

	}



	if(flag==0){
		$.ajax({

			method: "POST",
			url: "addComment",
			data: $('#commentform_'+id).serialize() 
		})
		.done(function( msg ) {
		
			var txt2 = $("<div></div>").html(msg);

			$('#comments_'+id).append(txt2);
			$('#commentDescription_'+id).val("");
		});
		$('#commentDescription_'+id).removeClass("red");
		cError.hide();
	}


}


function addPost(id){
	$("#errorPost").show();
	var flag =0;
	var post=$('#postDescription').val().trim();
	var pError =$("#errorPost");
	if(post == "")
	{
		$('#postDescription').addClass("red");
		flag =1;
		pError.text("Post is empty Write something to share...");

	}



	if(flag == 0){
		$.ajax({
			method: "POST",
			url: "addPost",
			data: $('#postForm').serialize() 
		})
		.done(function( msg ) {

			var txt = $("<div></div>").html(msg);

			$('#newPost').prepend(txt);

			$('#postDescription').val("");
		});
		$('#postDescription').removeClass("red");
		$('#selectgroup').hide();
		$("#errorPost").hide();
	}

}

$('#postDescription').focus(function(){
	$('#selectgroup').show();
	$('#postDescription').removeClass("red");
	$("#errorPost").text("");
});

function updatePost(id){

	var flag =0;
	var post=$('#postDescription').val().trim();
	var pError =$("#errorPost");
	if(post == "")
	{
		$('#postDescription').addClass("red");
		flag =1;
		pError.text("Post is empty Write something to share...");

	}



	if(flag == 0){
		var action = "updatePost?id"+id;
		$("#postForm").attr("action", action);
		$('#postForm').submit();

		$('#postDescription').removeClass("red");
	}




}

function updateComment(id){

	$("#errorcomment").show();
	var flag =0;
	

	var comment=$('#commentDescription').val().trim();
	var cError =$("#errorComment");
	
	if(comment == "")
	{
		$('#commentDescription').addClass("red");
		flag =1;
		cError.text("Comment is empty Write something...");

	}


	if(flag == 0){
		var action = "updateComment?commentid="+id;
		$("#commentForm").attr("action", action);
		$('#commentForm').submit();

		$('#commentDescription').removeClass("red");

	}




}
function addPostFromGroupPage(id){


	var flag =0;
	var post=$('#postDescription').val().trim();
	var pError =$("#errorPost");
	if(post == "")
	{
		$('#postDescription').addClass("red");
		flag =1;
		pError.text("Post is empty Write something to share...");

	}



	if(flag ==0)
	{ 
		$.ajax({
			method: "POST",
			url: "addPost",
			data: $('#postFromGroupPage').serialize() 
		})
		.done(function( msg ) {

			var txt = $("<div></div>").html(msg);

			$('#newPost').prepend(txt);
			$('#postDescription').val("");
		});
		$('#postDescription').removeClass("red");
		$('#selectgroup').hide();
		$("#errorPost").hide();
	}




}







