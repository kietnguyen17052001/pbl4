package Model.DAO;

import java.sql.*;
import java.util.*;
import java.util.Date;

import Context.ConnectDB;
import Model.BEAN.*;

public class Post_Photo_DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private static Post_Photo_DAO instance;

	public static Post_Photo_DAO getInstance() {
		if (instance == null) {
			instance = new Post_Photo_DAO();
		}
		return instance;
	}

	private Post_Photo_DAO() {
	}

	// get post_photo by postId
	public Post_Photo getPostPhoto(int postId) throws Exception {
		String query = "Select * from User_Post_Photo where postId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, postId);
		rs = ps.executeQuery();
		rs.next();
		return new Post_Photo(postId, rs.getInt("userId"), rs.getString("content"), rs.getString("photo"),
				rs.getObject("createDate"), rs.getObject("updateDate"));
	}

	// post photo
	public void postPhoto(Post_Photo postPhoto) throws Exception {
		String query = "Insert into User_Post_Photo values(?,?,?,?,?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, postPhoto.getUser_id());
		ps.setString(2, postPhoto.getContent());
		ps.setString(3, postPhoto.getPhoto());
		ps.setObject(4, postPhoto.getCreate_date());
		ps.setObject(5, postPhoto.getUpdate_date());
		ps.executeUpdate();
	}

	// edit post photo
	public void editPostPhoto(int postId, String newContent) throws Exception {
		String query = "Update User_Post_Photo set content = ?, updateDate = ? where postId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, newContent);
		ps.setObject(2, new Date());
		ps.setInt(3, postId);
		ps.executeUpdate();
	}

	// delete post photo
	public void deletePostPhoto(int postId) throws Exception {
		String query = "Delete from User_Post_Photo where postId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, postId);
		ps.executeUpdate();
	}

	// list post in user profile
	public List<Post_Photo> listPost(int userId) throws Exception {
		List<Post_Photo> listPost = new ArrayList<Post_Photo>();
		Post_Photo postPhoto = null;
		int postId;
		String content, photo;
		Object createDate, updateDate;
		String query = "Select * from User_Post_Photo where userId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		while (rs.next()) {
			postId = rs.getInt("postId");
			content = rs.getString("content");
			photo = rs.getString("photo");
			createDate = rs.getObject("createDate");
			updateDate = rs.getObject("updateDate");
			postPhoto = new Post_Photo(postId, userId, content, photo, createDate, updateDate);
			listPost.add(postPhoto);
		}
		return listPost;
	}
}
