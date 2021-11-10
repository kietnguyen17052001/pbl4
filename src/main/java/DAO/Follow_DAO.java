package DAO;

public class Follow_DAO {
	private static Follow_DAO instance;

	public static Follow_DAO getInstance() {
		if (instance == null) {
			instance = new Follow_DAO();
		}
		return instance;
	}

	private Follow_DAO() {
	}
}
