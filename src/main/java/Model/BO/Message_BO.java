package Model.BO;

import java.util.List;

import Model.BEAN.Message;
import Model.DAO.Message_DAO;

public class Message_BO {
	private static Message_BO instance;

	public static Message_BO getInstance() {
		if (instance == null) {
			instance = new Message_BO();
		}
		return instance;
	}

	private Message_BO() {
	}

	// add new message
	public void addMessage(Message message) throws Exception {
		Message_DAO.getInstance().addMessage(message);
	}

	// get list message
	public List<Message> listMessageInConversation(int conversationId) throws Exception {
		return Message_DAO.getInstance().listMessageInConversation(conversationId);
	}
}
