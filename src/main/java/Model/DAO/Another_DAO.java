package Model.DAO;

import java.sql.*;
import java.util.*;
import Context.ConnectDB;
import Model.BEAN.Post_Photo;
import Model.BEAN.User;

public class Another_DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private String querySelect = "Select * from Users";
	private static Another_DAO instance;

	public static Another_DAO getInstance() throws Exception {
		if (instance == null) {
			instance = new Another_DAO();
		}
		return instance;
	}

	private Another_DAO() {
	}

	// ----- get list -----
	// get list 5 explore
	public List<User> listTopExplore(int userId) throws Exception {
		List<User> listTop = new ArrayList<User>();
		String query = "Select * from Users where userId <> ? order by follower DESC";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		while (rs.next()) {
			if (!Follow_DAO.getInstance().isFollowed(userId, rs.getInt("userId"))) {
				listTop.add(User_DAO.getInstance().getUserById(rs.getInt("userId")));
			}
		}
		if (listTop.size() <= 5)
			return listTop;
		else {
			List<User> newListTop = new ArrayList<User>();
			for (User user : listTop) {
				if (newListTop.size() == 5) {
					break;
				} else {
					newListTop.add(user);
				}
			}
			return newListTop;
		}
	}

	// get list explore
	public List<User> listExplore(int userId) throws Exception {
		List<User> list = new ArrayList<User>();
		String query = "Select * from Users where userId <> ? order by follower DESC";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(User_DAO.getInstance().getUserById(rs.getInt("userId")));
		}
		return list;
	}

	// get list user when search
	public List<User> listUserSearch(String contentSearch) throws Exception {
		List<User> listUser = new ArrayList<User>();
		User user = null;
		int userId;
		String query = querySelect + " where firstName like ? or lastName like ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, "%" + contentSearch + "%");
		ps.setString(2, "%" + contentSearch + "%");
		rs = ps.executeQuery();
		while (rs.next()) {
			userId = rs.getInt("userId");
			user = User_DAO.getInstance().getUserById(userId);
			System.out.println(user.getFirst_name());
			listUser.add(user);
		}
		return listUser;
	}

	// get list post photo of user's following
	public HashMap<Post_Photo, User> listPostPhotoOfFollowing(int userId) throws Exception {
		HashMap<Post_Photo, User> hashMap = new HashMap<Post_Photo, User>();
		String query = "Select User_Post_Photo.postId, User_Post_Photo.userId from User_Post_Photo inner join Follow on User_Post_Photo.userId = Follow.targetId where Follow.userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		while (rs.next()) {
			hashMap.put(Post_Photo_DAO.getInstance().getPostPhoto(rs.getInt("postId")),
					User_DAO.getInstance().getUserById(rs.getInt("userId")));
		}
		System.out.println(hashMap.size());
		return hashMap;
	}
}
