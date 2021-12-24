package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Context.ConnectDB;
import Model.BEAN.Message;

public class Message_DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private static Message_DAO instance;

	public static Message_DAO getInstance() {
		if (instance == null) {
			instance = new Message_DAO();
		}
		return instance;
	}

	private Message_DAO() {
	}

	// add new message
	public void addMessage(Message message) throws Exception {
		String query = "Insert into Message values(?,?,?,?,?)";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, message.getConversation_id());
		ps.setInt(2, message.getSender_id());
		ps.setInt(3, message.getReceiver_id());
		ps.setString(4, message.getContent());
		ps.setObject(5, new Date());
		ps.executeUpdate();
	}

	// get list message
	public List<Message> listMessageInConversation(int conversationId) throws Exception {
		List<Message> list = new ArrayList<Message>();
		String query = "Select * from Message where conservationId = ?";
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, conversationId);
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getObject(6),
					rs.getString(5)));
		}
		return list;
	}
}
