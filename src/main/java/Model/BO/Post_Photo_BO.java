package Model.BO;

import java.util.*;

import Model.BEAN.Post_Photo;
import Model.DAO.Post_Photo_DAO;

public class Post_Photo_BO {
	private static Post_Photo_BO instance;

	public static Post_Photo_BO getInstance() {
		if (instance == null) {
			instance = new Post_Photo_BO();
		}
		return instance;
	}

	private Post_Photo_BO() {
	}

	// post photo
	public void postPhoto(Post_Photo postPhoto) throws Exception {
		Post_Photo_DAO.getInstance().postPhoto(postPhoto);
	}

	// edit post photo
	public void editPostPhoto(Post_Photo postPhoto) throws Exception {
		Post_Photo_DAO.getInstance().editPostPhoto(postPhoto);
	}

	// delete post
	public void deletePostPhoto(int postId) throws Exception {
		Post_Photo_DAO.getInstance().deletePostPhoto(postId);
	}

	// list post in user profile
	public List<Post_Photo> listPost(int userId) throws Exception {
		return Post_Photo_DAO.getInstance().listPost(userId);
	}
}
