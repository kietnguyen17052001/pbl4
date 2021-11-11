package Model.BEAN;

public class Conversation {
	private int conversation_id;
	private int first_userId;
	private int second_userId;
	private Object create_date;

	public Conversation(int conversation_id, int first_userId, int second_userId, Object create_date) {
		this.conversation_id = conversation_id;
		this.first_userId = first_userId;
		this.second_userId = second_userId;
		this.create_date = create_date;
	}

	public int getConversation_id() {
		return conversation_id;
	}

	public void setConversation_id(int conversation_id) {
		this.conversation_id = conversation_id;
	}

	public int getFirst_userId() {
		return first_userId;
	}

	public void setFirst_userId(int first_userId) {
		this.first_userId = first_userId;
	}

	public int getSecond_userId() {
		return second_userId;
	}

	public void setSecond_userId(int second_userId) {
		this.second_userId = second_userId;
	}

	public Object getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Object create_date) {
		this.create_date = create_date;
	}

}
