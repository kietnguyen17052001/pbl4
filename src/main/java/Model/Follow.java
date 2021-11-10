package Model;

public class Follow {
	private int follow_id;
	private int user_id;
	private int target_id;
	private Object follow_date;

	public Follow(int follow_id, int user_id, int target_id, Object follow_date) {
		this.follow_id = follow_id;
		this.user_id = user_id;
		this.target_id = target_id;
		this.follow_date = follow_date;
	}

	public int getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(int follow_id) {
		this.follow_id = follow_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTarget_id() {
		return target_id;
	}

	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}

	public Object getFollow_date() {
		return follow_date;
	}

	public void setFollow_date(Object follow_date) {
		this.follow_date = follow_date;
	}

}
