package Model.BEAN;

import java.util.Date;

public class Call extends Message_Call {
	private int call_time;

	public Call(int id, int conversation_id, int sender_id, int receiver_id, Date create_date, int call_time) {
		super(id, conversation_id, sender_id, receiver_id, create_date);
		this.call_time = call_time;
	}

	public int getCall_time() {
		return call_time;
	}

	public void setCall_time(int call_time) {
		this.call_time = call_time;
	}

}
