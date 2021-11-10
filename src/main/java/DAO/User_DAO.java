package DAO;

import java.security.*;
import java.sql.*;

import Context.ConnectDB;
import Model.User;

public class User_DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
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

	// insert new user
	public void add_user(User user) throws Exception {
		String query = "Insert into Users values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, user.getUser_type());
		ps.setString(2, encrypt(user.getUser_name()));
		ps.setString(3, user.getFirst_name());
		ps.setString(4, user.getLast_name());
		ps.setInt(5, user.getGender());
		ps.setString(6, encrypt(user.getPassword()));
		ps.setString(7, encrypt(user.getEmail()));
		ps.setString(8, user.getPhone());
		ps.setString(9, user.getCity());
		ps.setObject(10, user.getBirthday());
		ps.setBytes(11, user.getPhoto());
		ps.setString(12, user.getAbout());
		ps.setString(13, user.getPassion());
		ps.setString(14, user.getJob());
		ps.setString(15, user.getCompany());
		ps.setString(16, user.getFacebook());
		ps.setString(17, user.getInstagram());
		ps.setString(18, user.getUser_status());
		ps.setInt(19, user.getFollowing());
		ps.setInt(20, user.getFollower());
		ps.setObject(21, user.getRegistered_date());
		ps.setObject(22, user.getUpdated_date());
		ps.executeUpdate();
	}

	// login
	public boolean login(String username_phone_mail, String password) throws Exception {
		String query = "Select * from Users where (userName = ? or mobilePhone = ? or email = ?) and password_sha = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, encrypt(username_phone_mail));
		ps.setString(2, username_phone_mail);
		ps.setString(3, encrypt(username_phone_mail));
		ps.setString(4, encrypt(password));
		rs = ps.executeQuery();
		return rs.next();
	}
}
