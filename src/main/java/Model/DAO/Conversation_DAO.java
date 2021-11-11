package Model.DAO;

public class Conversation_DAO {
	private static Conversation_DAO instance;

	public static Conversation_DAO getInstance() {
		if (instance == null) {
			instance = new Conversation_DAO();
		}
		return instance;
	}

	private Conversation_DAO() {
	}
}
