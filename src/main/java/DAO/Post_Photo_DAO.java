package DAO;

public class Post_Photo_DAO {
	private static Post_Photo_DAO instance;

	public static Post_Photo_DAO getInstance() {
		if (instance == null) {
			instance = new Post_Photo_DAO();
		}
		return instance;
	}

	private Post_Photo_DAO() {
	}
}
