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

	// exist username or phone or email when registration
	public boolean isExistUsername_phone_mail(String information) throws Exception {
		return User_DAO.getInstance().isExistUsername_phone_mail(information);
	}

	// add user
	public void addUser(User user) throws Exception {
		User_DAO.getInstance().addUser(user);
	}

	// login
	public boolean isExistUser(String username_phone_mail, String password) throws Exception {
		return User_DAO.getInstance().isExistUser(username_phone_mail, password);
	}

	// get id user
	public String getIdUser(String username_phone_mail) throws Exception {
		return User_DAO.getInstance().getUserId(username_phone_mail);
	}

	// get user
	public User getUserById(String userId) throws Exception {
		return User_DAO.getInstance().getUserById(userId);
	}
}
