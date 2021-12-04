package Model.BO;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.DAO.Another_DAO;

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
	public LinkedHashMap<User, String> listUserSearch(int userId, String contentSearch) throws Exception {
		return Another_DAO.getInstance().listUserSearch(userId, contentSearch);
	}

	// get list top 5 explore
	public List<User> listTopExplore(int userId) throws Exception {
		return Another_DAO.getInstance().listTopExplore(userId);
	}

	// get list explore
	public HashMap<User, String> listExplore(int userId) throws Exception {
		return Another_DAO.getInstance().listExplore(userId);
	}

	// get list post photo of user's following
	public LinkedHashMap<Post_Photo, User> listPostPhotoOfFollowing(int userId) throws Exception {
		return Another_DAO.getInstance().listPostPhotoOfFollowing(userId);
	}
}
