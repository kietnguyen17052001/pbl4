package Model.BEAN;

public class Message_Call {
	private int id;
	private int conversation_id;
	private int sender_id;
	private int receiver_id;
	private Object create_date;

	public Message_Call(int id, int conversation_id, int sender_id, int receiver_id, Object create_date) {
		this.id = id;
		this.conversation_id = conversation_id;
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.create_date = create_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConversation_id() {
		return conversation_id;
	}

	public void setConversation_id(int conversation_id) {
		this.conversation_id = conversation_id;
	}

	public int getSender_id() {
		return sender_id;
	}

	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}

	public int getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}

	public Object getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Object create_date) {
		this.create_date = create_date;
	}

}
