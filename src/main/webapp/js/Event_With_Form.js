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
		document.getElementById("submit-delete-post").action = "Post_Photo_Controller?type=delete&postId=" + postId + "&userId=" + userId;
	}
	document.getElementById("form-post-photo-content").style.display = "block";
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
	console.log(newFollowerNumber);
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

