package Model.BO;

import java.util.List;

import Model.BEAN.User;
import Model.DAO.Follow_DAO;

public class Follow_BO {
	private static Follow_BO instance;

	public static Follow_BO getInstance() {
		if (instance == null) {
			instance = new Follow_BO();
		}
		return instance;
	}

	private Follow_BO() {
	}

	// list following, follower
	public List<User> listFollowingOrFollowerInProfile(int userId, boolean isFollowing) throws Exception {
		return Follow_DAO.getInstance().listFollowingOrFollowerInProfile(userId, isFollowing);
	}

	// check is followed?
	public boolean isFollowed(int userId, int targetId) throws Exception {
		return Follow_DAO.getInstance().isFollowed(userId, targetId);
	}

	// add new follow
	public void addNewFollow(int userId, int targetId) throws Exception {
		Follow_DAO.getInstance().addNewFollow(userId, targetId);
	}

	// unfollow
	public void unFollow(int userId, int targetId) throws Exception {
		Follow_DAO.getInstance().unFollow(userId, targetId);
	}

	// get date follow
	public String getDateFollow(int userId, int targetId) throws Exception {
		return Follow_DAO.getInstance().getDateFollow(userId, targetId);
	}
}
