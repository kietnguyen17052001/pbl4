package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.BO.Follow_BO;
import Model.BO.Post_Photo_BO;
import Model.BO.User_BO;
import Model.DAO.User_DAO;

/**
 * Servlet implementation class User_Controller
 */
@MultipartConfig
@WebServlet("/User_Controller")
public class User_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		int userId;
		User user;
		switch (type) {
		case "login": // login APP
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				if (User_BO.getInstance().isExistUser(username, password)) {
					userId = User_BO.getInstance().getIdUser(username);
					User_BO.getInstance().changeUserStatus(userId, true); // true: status=online
					request.setAttribute("userId", userId);
					sendDataListFollowerHistory(request, response, userId);
					getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
				} else {
					getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		case "create": // create new user
			try {
				if (isExistUsername_phone_mail(request, response)) { // username or phone or email exist in db
					getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
				} else {
					createUser(request, response);
					getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "edit": // edit profile
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				changeAvatar(request, response);
				editUser(request, response, userId);
				sendDataListFollowerHistory(request, response, userId);
				getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				try {
					editUser(request, response, userId);
					sendDataListFollowerHistory(request, response, userId);
					getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
				} catch (Exception e1) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case "search":
			userId = Integer.parseInt(request.getParameter("userId"));
			String contentSearch = request.getParameter("contentSearch");
			try {
				List<User> listUser = User_BO.getInstance().listUserSearch(contentSearch);
				HashMap<User, Boolean> hashMapp = hashMap(listUser, userId, true);
				request.setAttribute("userId", userId);
				sendDataListFollowerHistory(request, response, userId);
				request.setAttribute("hashMapp", hashMapp);
				getServletContext().getRequestDispatcher("/ResultSearchPage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		case "changePassword": // change password
			userId = Integer.parseInt(request.getParameter("userId"));
			String oldPassword = request.getParameter("oldpassword");
			try {
				user = User_BO.getInstance().getUserById(userId);
				if (User_BO.getInstance().checkOldPassword(userId, oldPassword)) {
					String newPassword = request.getParameter("newpassword");
					User_BO.getInstance().changePassword(userId, newPassword);
					request.setAttribute("user", user);
					sendDataListFollowerHistory(request, response, userId);
					getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
				} else {
					request.setAttribute("user", user);
					getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "homePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				request.setAttribute("userId", userId);
				sendDataListFollowerHistory(request, response, userId);
				getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "messagePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				request.setAttribute("userId", userId);
				sendDataListFollowerHistory(request, response, userId);
				getServletContext().getRequestDispatcher("/MessagePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "anotherProfilePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			int anotherUserId = Integer.parseInt(request.getParameter("anotherUserId"));
			try {
				User anotherUser = User_BO.getInstance().getUserById(anotherUserId);
				List<Post_Photo> listPostOfAnotherUser = Post_Photo_BO.getInstance().listPost(anotherUserId);
				List<User> listFollowingOfAnotherUser = Follow_BO.getInstance()
						.listFollowingOrFollowerInProfile(anotherUserId, true);
				List<User> listFollowerOfAnotherUser = Follow_BO.getInstance()
						.listFollowingOrFollowerInProfile(anotherUserId, false);
				HashMap<User, Boolean> hashMapListFollowingOfAnotherUser = hashMap(listFollowingOfAnotherUser, userId,
						false);
				HashMap<User, Boolean> hashMapListFollowerOfAnotherUser = hashMap(listFollowerOfAnotherUser, userId,
						false);
				boolean isFollowed = Follow_BO.getInstance().isFollowed(userId, anotherUserId); // check user is
																								// following anotherUser
				sendDataListFollowerHistory(request, response, userId);
				request.setAttribute("userId", userId);
				request.setAttribute("anotherUser", anotherUser);
				request.setAttribute("isFollowed", isFollowed);
				request.setAttribute("listPost", listPostOfAnotherUser);
				request.setAttribute("hashMapListFollowing", hashMapListFollowingOfAnotherUser);
				request.setAttribute("hashMapListFollower", hashMapListFollowerOfAnotherUser);
				getServletContext().getRequestDispatcher("/AnotherProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "profilePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				user = User_BO.getInstance().getUserById(userId);
				List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
				List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
				List<User> listFollowerr = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
				HashMap<User, String> hashMapp = new HashMap<User, String>();
				for (User follower : listFollowerr) {
					hashMapp.put(follower, Follow_BO.getInstance().getDateFollow(follower.getUser_id(), userId));
				}
				request.setAttribute("user", user);
				request.setAttribute("listFollowing", listFollowing);
				request.setAttribute("listFollower", listFollowerr);
				request.setAttribute("listPost", listPost);
				request.setAttribute("hashMap", hashMapp);
				getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "changePasswordPage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				user = User_BO.getInstance().getUserById(userId);
				request.setAttribute("user", user);
				sendDataListFollowerHistory(request, response, userId);
				getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "editProfilePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				user = User_BO.getInstance().getUserById(userId);
				request.setAttribute("user", user);
				sendDataListFollowerHistory(request, response, userId);
				getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "logout":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				User_BO.getInstance().changeUserStatus(userId, false);// false: status=offline
				getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// create new user
	public void createUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String birthday = request.getParameter("day") + "/" + request.getParameter("month") + "/"
				+ request.getParameter("year");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dBirthday = sdf.parse(birthday);
		User user = new User(1, "user", username, firstname, lastname, gender, password, email, phone, "", dBirthday,
				"user.jpg", "", "", "", "", "", "", "offline", 0, 0, 0, new Date(), new Date());
		// call add_user() in User_DAO
		User_BO.getInstance().addUser(user);
	}

	// change avatar
	public void changeAvatar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int userId = Integer.parseInt(request.getParameter("userId"));
		Part file = request.getPart("image");
		String imageFileName = file.getSubmittedFileName();
		String uploadPath = "D:/Dropbox/Season3 - DUT/PBL4/Code/AppPBL4/src/main/webapp/image/" + imageFileName;
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		User_BO.getInstance().changeAvatar(userId, imageFileName);
	}

	// edit user
	public void editUser(HttpServletRequest request, HttpServletResponse response, int userId) throws Exception {
		User user = User_DAO.getInstance().getUserById(userId);
		request.setAttribute("user", user);
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String about = request.getParameter("about");
		String job = request.getParameter("job");
		String company = request.getParameter("company");
		String facebook = request.getParameter("facebook");
		String instagram = request.getParameter("instagram");
		Object updateDate = new Date();
		user.setLast_name(lastName);
		user.setFirst_name(firstName);
		user.setGender(gender);
		user.setEmail(email);
		user.setPhone(phone);
		user.setCity(city);
		user.setAbout(about);
		user.setJob(job);
		user.setCompany(company);
		user.setFacebook(facebook);
		user.setInstagram(instagram);
		user.setUpdated_date(updateDate);
		User_DAO.getInstance().editUser(user);
	}

	// ---- functions ----
	// send data list follower history to form notification
	public void sendDataListFollowerHistory(HttpServletRequest request, HttpServletResponse response, int userId) throws Exception {
		List<User> listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
		HashMap<User, String> hashMap = new HashMap<User, String>();
		for (User follower : listFollower) {
			hashMap.put(follower, Follow_BO.getInstance().getDateFollow(follower.getUser_id(), userId));
		}
		request.setAttribute("hashMap", hashMap);
	}
	// check exist username or phone or mail
	public boolean isExistUsername_phone_mail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		return (User_BO.getInstance().isExistUsername_phone_mail(username)
				|| User_BO.getInstance().isExistUsername_phone_mail(phone)
				|| User_BO.getInstance().isExistUsername_phone_mail(email)) ? true : false;
	}

	// hashMap get user following and user not following in form list user following
	// and form list user follower
	public HashMap<User, Boolean> hashMap(List<User> listUser, int userId, boolean isProfilePage) throws Exception {
		HashMap<User, Boolean> hashMap = new HashMap<User, Boolean>();
		for (User u : listUser) {
			// remove user has id == userId
			if (isProfilePage) {
				if (u.getUser_id() == userId) {
					listUser.remove(u);
				}
			}
			if (Follow_BO.getInstance().isFollowed(userId, u.getUser_id())) {
				hashMap.put(u, true); // user is following target
			} else {
				hashMap.put(u, false); // user isn't following target
			}
		}
		return hashMap;
	}
}
