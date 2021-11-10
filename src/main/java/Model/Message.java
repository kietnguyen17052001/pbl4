package Model;

import java.util.Date;

public class Message extends Message_Call {
	private String content;

	public Message(int id, int conversation_id, int sender_id, int receiver_id, Date create_date, String content) {
		super(id, conversation_id, sender_id, receiver_id, create_date);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
