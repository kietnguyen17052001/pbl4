package Model.BEAN;

public class Post_Photo {
	private int post_id;
	private int user_id;
	private String content;
	private byte[] photo;
	private Object create_date;
	private Object update_date;

	public Post_Photo(int post_id, int user_id, String content, byte[] photo, Object create_date, Object update_date) {
		this.post_id = post_id;
		this.user_id = user_id;
		this.content = content;
		this.photo = photo;
		this.create_date = create_date;
		this.update_date = update_date;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Object getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Object create_date) {
		this.create_date = create_date;
	}

	public Object getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Object update_date) {
		this.update_date = update_date;
	}

}
