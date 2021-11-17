package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Context.ConnectDB;
import Model.BEAN.Following_Post;
import Model.BEAN.User;

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
	
	public List<User> getFollowing(int id) throws Exception{
		List<User> listFollowing = new ArrayList<User>();
		String query = "select * from Follow where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while(rs.next()) {
			listFollowing.add(User_DAO.getInstance().getUserById(rs.getInt("targetId")));
		}
		return listFollowing;
	}
	
	public ArrayList<Integer> getIdFollowing(int id) throws Exception{
		ArrayList<Integer> listIdFollowing = new ArrayList<Integer>();
		String query = "select * from Follow where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while(rs.next()) {
			listIdFollowing.add(rs.getInt("targetId"));
		}
		return listIdFollowing;
	}
	
	public ArrayList<Following_Post> getFollowingPost(ArrayList<Integer> listIdFollowing) throws Exception{
		ArrayList<Following_Post> listFollowingPost = new ArrayList<Following_Post>();
		for(int i : listIdFollowing) {
			String query = "select Users.photo, Users.firstName, Users.lastName, content, User_Post_Photo.photo, createDate from Users inner join User_Post_Photo on Users.userId = User_Post_Photo.userId and Users.userId = ?";
			conn = new ConnectDB().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, i);
			rs = ps.executeQuery();
			while(rs.next()) {
				listFollowingPost.add(new Following_Post(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getObject(6)));
			}
		}
		return listFollowingPost;
	}
}
