package Model.BO;

import Model.BEAN.User;
import Model.DAO.User_DAO;

public class User_BO {
	private static User_BO instance;

	public static User_BO getInstance() {
		if (instance == null) {
			instance = new User_BO();
		}
		return instance;
	}

	private User_BO() {
	}

	// add user
	public void addUser(User user) throws Exception {
		User_DAO.getInstance().addUser(user);
	}

	// login
	public boolean login(String username_phone_mail, String password) throws Exception {
		return User_DAO.getInstance().login(username_phone_mail, password);
	}
}
