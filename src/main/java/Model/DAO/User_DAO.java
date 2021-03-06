package Model.DAO;

import java.security.*;
import java.sql.*;
import Context.ConnectDB;
import Model.BEAN.User;

public class User_DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private String querySelect = "Select * from Users";
	private static User_DAO instance;

	public static User_DAO getInstance() throws Exception {
		if (instance == null) {
			instance = new User_DAO();
		}
		return instance;
	}

	private User_DAO() throws Exception {
	}

	// encrypt username & password by algorithm SHA-256
	public String encrypt(String text) throws Exception {
		StringBuilder sbuilder = new StringBuilder();
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		mDigest.update(text.getBytes());
		byte[] b = mDigest.digest();
		for (byte _b : b) {
			sbuilder.append(Integer.toHexString(0xFF & _b));
		}
		return sbuilder.toString().toUpperCase();
	}

	// exist username or phone or mail
	public boolean isExistUsername_phone_mail(String information) throws Exception {
		String query = querySelect + " where userName = ? or mobilePhone = ? or email = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, encrypt(information));
		ps.setString(2, information);
		ps.setString(3, information);
		rs = ps.executeQuery();
		return rs.next();
	}

	// Is valid user when forgot password
	public User getUserIdByEmailAndPhone(String email, String phone) throws Exception {
		String query = querySelect + " where email = ? and mobilePhone = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, email);
		ps.setString(2, phone);
		rs = ps.executeQuery();
		if (rs.next()) {
			return getUserById(rs.getInt("userId"));
		} else {
			return null;
		}
	}

	// ----- end -----
	// insert new user
	public void addUser(User user) throws Exception {
		String query = "Insert into Users values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, user.getUser_type());
		ps.setString(2, encrypt(user.getUser_name()));
		ps.setString(3, user.getFirst_name());
		ps.setString(4, user.getLast_name());
		ps.setString(5, user.getFull_name());
		ps.setInt(6, user.getGender());
		ps.setString(7, encrypt(user.getPassword()));
		ps.setString(8, user.getEmail());
		ps.setString(9, user.getPhone());
		ps.setString(10, user.getCity());
		ps.setObject(11, user.getBirthday());
		ps.setString(12, user.getPhoto());
		ps.setString(13, user.getAbout());
		ps.setString(14, user.getPassion());
		ps.setString(15, user.getJob());
		ps.setString(16, user.getCompany());
		ps.setString(17, user.getFacebook());
		ps.setString(18, user.getInstagram());
		ps.setString(19, user.getUser_status());
		ps.setInt(20, user.getPost());
		ps.setInt(21, user.getFollowing());
		ps.setInt(22, user.getFollower());
		ps.setInt(23, user.getNewFollower());
		ps.setObject(24, user.getRegistered_date());
		ps.setObject(25, user.getUpdated_date());
		ps.executeUpdate();
	}

	// edit user
	public void editUser(User user) throws Exception {
		String query = "Update Users set firstName = ?, lastName = ?, fullName = ?, gender = ?, email = ?, mobilePhone = ?, city = ?, about = ?, job = ?, company = ?, facebook = ?, instagram = ?, updateDate = ? where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, user.getFirst_name());
		ps.setString(2, user.getLast_name());
		ps.setString(3, user.getFull_name());
		ps.setInt(4, user.getGender());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPhone());
		ps.setString(7, user.getCity());
		ps.setString(8, user.getAbout());
		ps.setString(9, user.getJob());
		ps.setString(10, user.getCompany());
		ps.setString(11, user.getFacebook());
		ps.setString(12, user.getInstagram());
		ps.setObject(13, user.getUpdated_date());
		ps.setInt(14, user.getUser_id());
		ps.executeUpdate();
	}

	// change avatar
	public void changeAvatar(int userId, String imageFileName) throws Exception {
		String query = "Update Users set photo = ? where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, imageFileName);
		ps.setInt(2, userId);
		ps.executeUpdate();
	}

	// update number of posts
	public void updateNumberOfPosts(int userId, boolean isAddPost) throws Exception {
		String query_select = "Select post from Users where userId = ?";
		String query_update = "Update Users set post = ? where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query_select);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		rs.next();
		int currentPostOfUser = isAddPost ? rs.getInt("post") + 1 : rs.getInt("post") - 1;
		ps = conn.prepareStatement(query_update);
		ps.setInt(1, currentPostOfUser);
		ps.setInt(2, userId);
		ps.executeUpdate();
	}

	// check old password
	public boolean checkOldPassword(int userId, String oldPassword) throws Exception {
		String query = "Select password_sha from Users where userId = ? and password_sha = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		ps.setString(2, encrypt(oldPassword));
		rs = ps.executeQuery();
		return rs.next();
	}

	// change password
	public void changePassword(int userId, String newPassword) throws Exception {
		String query = "Update Users set password_sha = ? where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, encrypt(newPassword));
		ps.setInt(2, userId);
		ps.executeUpdate();
	}

	// change user status
	public void changeUserStatus(int userId, boolean isLogin) throws Exception {
		String query = "Update Users set userStatus = ? where userId = ?";
		String status = isLogin ? "online" : "offline";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, status);
		ps.setInt(2, userId);
		ps.executeUpdate();
	}

	// login
	public boolean isExistUser(String username_phone_mail, String password) throws Exception {
		String query = querySelect + " where (userName = ? or mobilePhone = ? or email = ?) and password_sha = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, encrypt(username_phone_mail));
		ps.setString(2, username_phone_mail);
		ps.setString(3, username_phone_mail);
		ps.setString(4, encrypt(password));
		rs = ps.executeQuery();
		return rs.next();
	}

	// get user by username and password
	public User getUser(String username_phone_mail, String password) throws Exception {
		isExistUser(username_phone_mail, password);
		User user = new User(rs.getInt("userId"), rs.getString("userType"), rs.getString("userName"),
				rs.getString("firstName"), rs.getString("lastName"), rs.getString("fullName"), rs.getInt("gender"),
				rs.getString("password_sha"), rs.getString("email"), rs.getString("mobilePhone"), rs.getString("city"),
				rs.getObject("birthday"), rs.getString("photo"), rs.getString("about"), rs.getString("passions"),
				rs.getString("job"), rs.getString("company"), rs.getString("facebook"), rs.getString("instagram"),
				rs.getString("userStatus"), rs.getInt("post"), rs.getInt("following"), rs.getInt("follower"),
				rs.getInt("newFollower"), rs.getObject("registeredDate"), rs.getObject("updateDate"));
		return user;
	}

	// get user by id
	public User getUserById(int id) throws Exception {
		String query = querySelect + " where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		rs.next();
		User user = new User(rs.getInt("userId"), rs.getString("userType"), rs.getString("userName"),
				rs.getString("firstName"), rs.getString("lastName"), rs.getString("fullName"), rs.getInt("gender"),
				rs.getString("password_sha"), rs.getString("email"), rs.getString("mobilePhone"), rs.getString("city"),
				rs.getObject("birthday"), rs.getString("photo"), rs.getString("about"), rs.getString("passions"),
				rs.getString("job"), rs.getString("company"), rs.getString("facebook"), rs.getString("instagram"),
				rs.getString("userStatus"), rs.getInt("post"), rs.getInt("following"), rs.getInt("follower"),
				rs.getInt("newFollower"), rs.getObject("registeredDate"), rs.getObject("updateDate"));
		return user;
	}

	// reset new follower
	public void resetNewFollower(int userId) throws Exception {
		String query = "Update Users set newFollower = ? where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, 0);
		ps.setInt(2, userId);
		ps.executeUpdate();
	}
}
