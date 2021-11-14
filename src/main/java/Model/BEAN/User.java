package Model.BEAN;

public class User {
	private int user_id;
	private String user_type;
	private String user_name;
	private String first_name;
	private String last_name;
	private int gender;
	private String password;
	private String email;
	private String phone;
	private String city;
	private Object birthday;
	private String photo;
	private String about;
	private String passion;
	private String job;
	private String company;
	private String facebook;
	private String instagram;
	private String user_status;
	private int post; // so luong bai viet
	private int following; // so luong nguoi ma ban theo doi
	private int follower; // so luong nguoi theo doi ban
	private Object registered_date;
	private Object updated_date;

	public User(int user_id, String user_type, String user_name, String first_name, String last_name, int gender,
			String password, String email, String phone, String city, Object birthday, String photo, String about,
			String passion, String job, String company, String facebook, String instagram, String user_status, int post,
			int following, int follower, Object registered_date, Object updated_date) {
		this.user_id = user_id;
		this.user_type = user_type;
		this.user_name = user_name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.birthday = birthday;
		this.photo = photo;
		this.about = about;
		this.passion = passion;
		this.job = job;
		this.company = company;
		this.facebook = facebook;
		this.instagram = instagram;
		this.user_status = user_status;
		this.post = post;
		this.following = following;
		this.follower = follower;
		this.registered_date = registered_date;
		this.updated_date = updated_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Object getBirthday() {
		return birthday;
	}

	public void setBirthday(Object birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPassion() {
		return passion;
	}

	public void setPassion(String passion) {
		this.passion = passion;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public Object getRegistered_date() {
		return registered_date;
	}

	public void setRegistered_date(Object registered_date) {
		this.registered_date = registered_date;
	}

	public Object getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Object updated_date) {
		this.updated_date = updated_date;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

}
