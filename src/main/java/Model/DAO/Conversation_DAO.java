package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import Context.ConnectDB;
import Model.BEAN.Conversation;

public class Conversation_DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private static Conversation_DAO instance;

	public static Conversation_DAO getInstance() {
		if (instance == null) {
			instance = new Conversation_DAO();
		}
		return instance;
	}

	private Conversation_DAO() {
	}

	// check exist conversation
	public boolean isExistConversation(int userId, int targetId) throws Exception {
		String query = "Select * from Conversation where (first_UserId = ? and second_UserId = ?) or (second_UserId = ? and first_UserId = ?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		ps.setInt(2, targetId);
		ps.setInt(3, userId);
		ps.setInt(4, targetId);
		rs = ps.executeQuery();
		return rs.next();
	}

	// get conversation id
	public int getConversationId(int userId, int targetId) throws Exception {
		String query = "Select * from Conversation where (first_UserId = ? and second_UserId = ?) or (second_UserId = ? and first_UserId = ?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, userId);
		ps.setInt(2, targetId);
		ps.setInt(3, userId);
		ps.setInt(4, targetId);
		rs = ps.executeQuery();
		rs.next();
		return rs.getInt("conservationId");
	}

	// add new conversation
	public void addConversation(Conversation conversation) throws Exception {
		String query = "Insert into Conversation values(?,?,?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, conversation.getFirst_userId());
		ps.setInt(2, conversation.getSecond_userId());
		ps.setObject(3, new Date());
		ps.executeUpdate();
	}
}
