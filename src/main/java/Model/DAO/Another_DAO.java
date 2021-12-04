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
	public HashMap<User, String> listExplore(int userId) throws Exception {
		List<User> list = new ArrayList<User>();
		String query = "Select * from Users where userId <> ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		while (rs.next()) {
			if (!Follow_DAO.getInstance().isFollowed(userId, rs.getInt("userId"))) {
				list.add(User_DAO.getInstance().getUserById(rs.getInt("userId")));
			}
		}
		// hashmap: list explore, if another is following you, String = "Follow you"
		// else String = ...
		HashMap<User, String> hashMapAnother = new HashMap<User, String>();
		// list user's following
		List<User> listFollowing = Follow_DAO.getInstance().listFollowingOrFollowerInProfile(userId, true);
		int count;
		String s;
		for (User another : list) {
			if (Follow_DAO.getInstance().isFollowed(another.getUser_id(), userId)) {
				hashMapAnother.put(another, "Follow you");
			} else {
				count = 0;
				s = "";
				for (User following : listFollowing) {
					if (Follow_DAO.getInstance().isFollowed(following.getUser_id(), another.getUser_id())) {
						s = following.getFull_name();
						count++;
					}
				}
				if (count == 1) { // only 1 user's following is follow another
					hashMapAnother.put(another, "Have " + s + " follow");
				} else if (count >= 2) { // 2... more user's following is follow another
					count--;
					hashMapAnother.put(another, "Have " + s + " and " + count + " more followers");
				} else {
					hashMapAnother.put(another, s); // no user's following is follow another
				}
			}
		}
		return hashMapAnother;
	}

	// get list user when search
	public LinkedHashMap<User, String> listUserSearch(int userId, String contentSearch) throws Exception {
		LinkedHashMap<User, String> linkedHashMap = new LinkedHashMap<User, String>();
		// list user search
		List<User> listUser = new ArrayList<User>();
		// list user following
		List<User> listFollowing = Follow_DAO.getInstance().listFollowingOrFollowerInProfile(userId, true);
		User user = null;
		int anotherId;
		String query = querySelect + " where fullName like ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, "%" + contentSearch + "%");
		rs = ps.executeQuery();
		while (rs.next()) {
			anotherId = rs.getInt("userId");
			user = User_DAO.getInstance().getUserById(anotherId);
			listUser.add(user);
		}
		StringBuffer status_suggestion;
		int count;
		String followingFullName = "";
		for (User another : listUser) {
			status_suggestion = new StringBuffer();
			if (userId == another.getUser_id()) {
				status_suggestion.append("3,You");
				linkedHashMap.put(another, status_suggestion.toString());
				continue;
			} else if (Follow_DAO.getInstance().isFollowed(userId, another.getUser_id())) {
				status_suggestion.append("1"); // following
			} else if (!Follow_DAO.getInstance().isFollowed(userId, another.getUser_id())) {
				status_suggestion.append("0"); // not folowwing
			}
			if (Follow_DAO.getInstance().isFollowed(another.getUser_id(), userId)) {
				status_suggestion.append(",Follow you");
			}
			count = 0;
			// suggestion number of followings is follow another
			for (User following : listFollowing) {
				if (Follow_DAO.getInstance().isFollowed(following.getUser_id(), another.getUser_id())) {
					count++;
					followingFullName = following.getFull_name();
				}
			}
			if (count == 1) {
				status_suggestion.append(",Have " + followingFullName + " follow");
			} else if (count >= 2) {
				count--;
				status_suggestion.append(",Have " + followingFullName + " and " + count + " more followers");
			}
			linkedHashMap.put(another, status_suggestion.toString());
		}
		return linkedHashMap;
	}

	// get list post photo of user's following
	public LinkedHashMap<Post_Photo, User> listPostPhotoOfFollowing(int userId) throws Exception {
		LinkedHashMap<Post_Photo, User> linkedHashMap = new LinkedHashMap<Post_Photo, User>();
		String query = "Select User_Post_Photo.postId, User_Post_Photo.userId from User_Post_Photo inner join Follow on User_Post_Photo.userId = Follow.targetId where Follow.userId = ? order by User_Post_Photo.postId desc";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		while (rs.next()) {
			linkedHashMap.put(Post_Photo_DAO.getInstance().getPostPhoto(rs.getInt("postId")),
					User_DAO.getInstance().getUserById(rs.getInt("userId")));
		}
		return linkedHashMap;
	}
}
