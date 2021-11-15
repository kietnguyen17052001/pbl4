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

	// add new follow
	public void addNewFollow(int userId, int targetId) throws Exception {
		Follow_DAO.getInstance().addNewFollow(userId, targetId);
	}
}
