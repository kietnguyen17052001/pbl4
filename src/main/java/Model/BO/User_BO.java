package Model.BO;

import java.util.*;
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

	// edit user
	public void editUser(User user) throws Exception {
		User_DAO.getInstance().editUser(user);
	}

	// change avatar
	public void changeAvatar(int userId, String imageFileName) throws Exception {
		User_DAO.getInstance().changeAvatar(userId, imageFileName);
	}

	// update number of post
	public void updateNumberOfPost(int userId, boolean isAddPost) throws Exception {
		User_DAO.getInstance().updateNumberOfPosts(userId, isAddPost);
	}

	// check old password
	public boolean checkOldPassword(int userId, String oldPassword) throws Exception {
		return User_DAO.getInstance().checkOldPassword(userId, oldPassword);
	}

	// change password
	public void changePassword(int userId, String newPassword) throws Exception {
		User_DAO.getInstance().changePassword(userId, newPassword);
	}

	// login
	public boolean isExistUser(String username_phone_mail, String password) throws Exception {
		return User_DAO.getInstance().isExistUser(username_phone_mail, password);
	}

	// change user status
	public void changeUserStatus(int userId, boolean isLogin) throws Exception {
		User_DAO.getInstance().changeUserStatus(userId, isLogin);
	}

	// get id user
	public int getIdUser(String username_phone_mail) throws Exception {
		return User_DAO.getInstance().getUserId(username_phone_mail);
	}

	// get user
	public User getUserById(int userId) throws Exception {
		return User_DAO.getInstance().getUserById(userId);
	}

	// get list user when search
	public List<User> listUserSearch(String contentSearch) throws Exception {
		return User_DAO.getInstance().listUserSearch(contentSearch);
	}
}
