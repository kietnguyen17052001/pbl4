package Model.BO;

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
}
