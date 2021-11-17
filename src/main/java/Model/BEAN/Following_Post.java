package Model.BEAN;

public class Following_Post {

	private String avatar;
	private String firstName;
	private String lastName;
	private String content;
	private String photo;
	private Object createDate;
	
	public Following_Post(String avatar, String firstName, String lastName, String content, String photo
			, Object createDate) {
		this.avatar = avatar;
		this.firstName = firstName;
		this.lastName = lastName;
		this.content = content;
		this.photo = photo;
		this.createDate = createDate;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	
	public String getlastName() {
		return this.lastName;
	}
	
	public String getfirstName() {
		return this.firstName;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getPhoto() {
		return this.photo;
	}
	
	public Object getcreateDate() {
		return this.createDate;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public void setlastName(String lastname) {
		this.lastName = lastname;
	}
	
	public void setfirstName(String firstname) {
		this.firstName = firstname;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public void setcreateDate(Object createDate) {
		this.createDate = createDate;
	}
	
}
