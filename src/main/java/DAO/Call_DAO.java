package DAO;

public class Call_DAO {
	private static Call_DAO instance;

	public static Call_DAO getInstance() {
		if (instance == null) {
			instance = new Call_DAO();
		}
		return instance;
	}

	private Call_DAO() {
	}
}
