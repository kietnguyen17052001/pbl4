package Model.BO;

import java.util.HashMap;
import java.util.List;

import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.DAO.Another_DAO;
import Model.DAO.Post_Photo_DAO;
import Model.DAO.User_DAO;

public class Another_BO {
	private static Another_BO instance;

	public static Another_BO getInstance() {
		if (instance == null) {
			instance = new Another_BO();
		}
		return instance;
	}

	private Another_BO() {
	}

	// get list user when search
	public List<User> listUserSearch(String contentSearch) throws Exception {
		return Another_DAO.getInstance().listUserSearch(contentSearch);
	}

	// get list top 5 explore
	public List<User> listTopExplore(int userId) throws Exception {
		return Another_DAO.getInstance().listTopExplore(userId);
	}

	// get list explore
	public List<User> listExplore(int userId) throws Exception {
		return Another_DAO.getInstance().listExplore(userId);
	}

	// get list post photo of user's following
	public HashMap<Post_Photo, User> listPostPhotoOfFollowing(int userId) throws Exception {
		return Another_DAO.getInstance().listPostPhotoOfFollowing(userId);
	}
}
