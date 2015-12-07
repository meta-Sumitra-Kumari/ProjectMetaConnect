$(document).ready(function() {
	$('#LoadMorePost').click(function() {
	
		
		$.ajax({
			method : "GET",
			url : "PostLoading",
		}).done(function(msg) {
		});
	});
	
	$('#submitButton').click(function() {
		var groupId = $(this).data().groupid;
		$.ajax({
			method : "POST",
			url : "addMember",
			data : {
				id : groupId
			}
		}).done(function(msg) {
			$('#submitButton').html("Request sent");
			$('#submitButton').prop('disabled', true);
		});
	});

	$('#accept').click(function() {
		var id = $(this).data().id;
		alert(id);
		$.ajax({
			method : "POST",
			url : "acceptRequestForGroup",
			data : {
				id : id
			}
		}).done(function(msg) {
		});
	});

	$('#deny').click(function() {
		var id = $(this).data().id;
		alert(id);
		$.ajax({
			method : "POST",
			url : "deniedRequest",
			data : {
				id : id
			}
		}).done(function(msg) {
		});
	});

})

function addComment() {

	alert(id);
	$.ajax({
		method : "POST",
		url : "addComment",
		data : $('#commentform_' + id).serialize()
	}).done(function(msg) {
		var txt2 = $("<div></div>").html(msg);
		$('#comments_' + id).append(txt2);
		$('#commentDescription').val("");
	});

}
function loadmore() {
	$.ajax({
		method : "GET",
		url : "PostLoading",
		data : $('#postForm').serialize()
	}).done(function(msg) {
		alert("Data Saved: " + msg);
		var txt = $("<div></div>").html(msg);

		$('#newPost').append(txt);

		$('#postDescription').val("");
	});

}

function addPost(id) {


	$.ajax({
		method : "POST",
		url : "addPost",
		data : $('#postForm').serialize()
	}).done(function(msg) {
		var txt = $("<div></div>").html(msg);

		$('#newPost').prepend(txt);

		$('#postDescription').val("");
	});

}

function addPostFromGroupPage(id) {

	$.ajax({
		method : "POST",
		url : "addPost",
		data : $('#postFromGroupPage').serialize()
	}).done(function(msg) {
		alert("Data Saved: " + msg);
		var txt = $("<div></div>").html(msg);
		$('#newPost').prepend(txt);
		$('#postDescription').val("");
	});

}

/* http://localhost:8080/MetaConnect/addMember?groupid=3 */