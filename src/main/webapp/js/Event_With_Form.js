// event open-close form post photo
function openFormPostPhoto() {
	document.getElementById("form-post").style.display = "block";
}
function closeFormPostPhoto() {
	document.getElementById("form-post").style.display = "none";
}
// event open-close form post photo content
function openFormPostPhotoContent(contentPost, linkImage, createDate) {
	document.getElementById("post-photo-content-content").innerHTML = contentPost;
	document.getElementById("create-date").innerHTML = createDate;
	document.getElementById("photo").src = "image/" + linkImage;
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
}
function closeFormListFollowerHistory() {
	document.getElementById("form-follow-history").style.display = "none";
}

