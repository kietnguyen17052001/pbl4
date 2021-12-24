// event open-close form post photo
function openFormPostPhoto() {
	document.getElementById("form-post").style.display = "block";
}
function closeFormPostPhoto() {
	document.getElementById("form-post").style.display = "none";
}
// event open-close form post photo content
function openFormPostPhotoContent(type, userId, postId, contentPost, linkImage, createDate) {
	document.getElementById("post-photo-content-content").innerHTML = contentPost;
	document.getElementById("create-date").innerHTML = createDate;
	document.getElementById("photo").src = "image/" + linkImage;
	if (type == "user") {
		// delete post
		document.getElementById("submit-delete-post").action = "Post_Photo_Controller?type=delete&postId=" + postId + "&userId=" + userId;
		// id post
		document.getElementById("id-post").innerHTML = postId;
	}
	document.getElementById("form-post-photo-content").style.display = "block";
}
// open form edit content post
function openFormEdit() {
	document.getElementById("form-edit-content-post").style.display = "block";
}
// close form dit content post
function closeFormEdit() {
	document.getElementById("form-edit-content-post").style.display = "none";
}
// save change edit content
function saveChangePostContent() {
	var id = document.getElementById("id-post").textContent;
	var content = document.getElementById("content-edit").value;
	document.getElementById("post-photo-content-content").innerHTML = content;
	document.getElementById("form-edit-content-post").style.display = "none";
	// ajax
	$.ajax({
		url: '/AppPBL4/Post_Photo_Controller?type=edit',
		type: 'GET',
		data: {
			postId: id,
			newContent: content
		},
		success: function(response) {
		}
	});
}
function closeFormPostPhotoContent() {
	document.getElementById("form-post-photo-content").style.display = "none";
}
// event open-close form list following
function openFormListFollowing() {
	document.getElementById("form-following").style.display = "block";
}
function closeFormListFollowing() {
	document.getElementById("form-following").style.display = "none";
}
function openFormListFollower() {
	document.getElementById("form-follower").style.display = "block";
}
function closeFormListFollower() {
	document.getElementById("form-follower").style.display = "none";
}
// event open-close form list follower history
function openFormListFollowerHistory() {
	document.getElementById("form-follow-history").style.display = "block";
	var newFollowerNumber = document.getElementById("id-newFollowerNumber").innerHTML;
	if (newFollowerNumber > 0) {
		document.getElementById("id-newFollowerNumber").style.display = "none";
		// ajax
		$.ajax({
			url: '/AppPBL4/User_Controller?type=changeNewFollowerNumber',
			type: 'GET',
			success: function(response) {
			}
		});
	}
}
function closeFormListFollowerHistory() {
	document.getElementById("form-follow-history").style.display = "none";
}


// search
function openFormLoadSearch() {
	document.getElementById("form-load-search").style.display = "block";
}
function closeFormLoadSearch() {
	document.getElementById("form-load-search").style.display = "none";
}
function searchByName(param) {
	openFormLoadSearch();
	var content = param.value;
	$.ajax({
		url: '/AppPBL4/User_Controller?type=searchAjax',
		type: 'GET',
		data: {
			contentSearch: content
		},
		success: function(data) {
			var row = document.getElementById("load-content-search");
			row.innerHTML = data;
		}
	});
}

// chat
function chatBox(_userId, _targetId){
	$.ajax({
		url: '/AppPBL4/Message_Controller?type=chatBox',
		type: 'GET',
		data: {
			userId: _userId,
			targetId: _targetId 
		},
		success: function(data) {
			var row = document.getElementById("right-mess-box");
			row.innerHTML = data;
		}
	});
}
