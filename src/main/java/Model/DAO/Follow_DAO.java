package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import Context.ConnectDB;

public class Follow_DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private static Follow_DAO instance;

	public static Follow_DAO getInstance() {
		if (instance == null) {
			instance = new Follow_DAO();
		}
		return instance;
	}

	private Follow_DAO() {
	}

	// add new follow
	public void addNewFollow(int userId, int targetId) throws Exception {
		// insert object for table follow
		String query = "Insert into Follow values(?,?,?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		ps.setInt(2, targetId);
		ps.setObject(3, new Date());
		ps.executeUpdate();
		// update follow for user and target
		// for new follow
		updateFollow(userId, targetId, true);
	}

	// update follow
	public void updateFollow(int userId, int targetId, boolean isFollow) throws Exception {
		String query1 = "Select following from Users where userId = ?";
		String query2 = "Select follower from Users where userId = ?";
		String query3 = "Update Users set following = ? where userId = ?";
		String query4 = "Update Users set follower = ? where userId = ?";
		conn = new ConnectDB().getConnection();
		// get following of user and update + 1
		ps = conn.prepareStatement(query1);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		rs.next();
		// if isFollow == true => follow, else == false => unFollow
		int currentFollowingOfUser = isFollow ? rs.getInt("following") + 1 : rs.getInt("following") - 1;
		// get follower of target and update + 1
		ps = conn.prepareStatement(query2);
		ps.setInt(1, targetId);
		rs = ps.executeQuery();
		rs.next();
		int currentFollowerOfTarget = isFollow ? rs.getInt("follower") + 1 : rs.getInt("follower") - 1;
		// update user
		ps = conn.prepareStatement(query3);
		ps.setInt(1, currentFollowingOfUser);
		ps.setInt(2, userId);
		ps.executeUpdate();
		// update target
		ps = conn.prepareStatement(query4);
		ps.setInt(1, currentFollowerOfTarget);
		ps.setInt(2, targetId);
		ps.executeUpdate();
	}
}
