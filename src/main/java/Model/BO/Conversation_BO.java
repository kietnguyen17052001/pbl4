package Model.BO;

import Model.BEAN.Conversation;
import Model.DAO.Conversation_DAO;

public class Conversation_BO {
	private static Conversation_BO instance;

	public static Conversation_BO getInstance() {
		if (instance == null) {
			instance = new Conversation_BO();
		}
		return instance;
	}

	private Conversation_BO() {
	}

	// check exist conversation
	public boolean isExistConversation(int userId, int targetId) throws Exception {
		return Conversation_DAO.getInstance().isExistConversation(userId, targetId);
	}

	// get conversation id
	public int getConversationId(int userId, int targetId) throws Exception {
		return Conversation_DAO.getInstance().getConversationId(userId, targetId);
	}

	// add new conversation
	public void addConversation(Conversation conversation) throws Exception {
		Conversation_DAO.getInstance().addConversation(conversation);
	}
}
