package Controller;

import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.BO.Another_BO;
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
		HttpSession session = request.getSession();
		switch (type) {
		case "login": // login APP
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				if (User_BO.getInstance().isExistUser(username, password)) {
					user = User_BO.getInstance().getUser(username, password);
					// create and push user up session
					session.setAttribute("user", user);
					userId = user.getUser_id();
					directHomePage(request, response, userId);
				} else {
					getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "forgotPassword":
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			try {
				user = User_BO.getInstance().getUserIdByEmailAndPhone(email, phone);
				if (user != null) {
					userId = user.getUser_id();
					request.setAttribute("userId", userId);
					getServletContext().getRequestDispatcher("/ChangePasswordForgot.jsp").forward(request, response);
				} else {
					getServletContext().getRequestDispatcher("/ForgotPassword.jsp").forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
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
			user = (User) session.getAttribute("user");
			try {
				changeAvatar(request, response, user.getUser_id());
				editUser(request, response, user);
				sendDataListFollowerHistory(request, response, user.getUser_id());
				user = User_BO.getInstance().getUserById(user.getUser_id());
				session.setAttribute("user", user);
				getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				try {
					editUser(request, response, user);
					sendDataListFollowerHistory(request, response, user.getUser_id());
					user = User_BO.getInstance().getUserById(user.getUser_id());
					session.setAttribute("user", user);
					getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
			break;
		case "search":
			String contentSearch = request.getParameter("contentSearch");
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			try {
				List<User> listUser = Another_BO.getInstance().listUserSearch(contentSearch);
				HashMap<User, Boolean> hashMap = hashMap(listUser, userId, true);
				sendDataListFollowerHistory(request, response, userId);
				request.setAttribute("hashMapp", hashMap);
				getServletContext().getRequestDispatcher("/ResultSearchPage.jsp").forward(request, response);
			} catch (Exception e) {
				try {
					directHomePage(request, response, user.getUser_id());
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		case "changePassword": // change password
			String subType = request.getParameter("subType");
			String oldPassword = request.getParameter("oldpassword");
			String newPassword = request.getParameter("newpassword");
			if (subType.equals("changePasswordPage")) {
				user = (User) session.getAttribute("user");
				userId = user.getUser_id();
			} else { // change password when forgot password
				userId = Integer.parseInt(request.getParameter("userId"));
			}
			try {
				if (User_BO.getInstance().checkOldPassword(userId, oldPassword)) { // invalid old password
					User_BO.getInstance().changePassword(userId, newPassword);
					if (subType.equals("changePasswordPage")) {
						sendDataListFollowerHistory(request, response, userId);
						session.setAttribute("user", User_BO.getInstance().getUserById(userId));
						getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
					} else {
						getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
					}
				} else {
					if (subType.equals("changePasswordPage")) {
						getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
					} else {
						request.setAttribute("userId", userId);
						getServletContext().getRequestDispatcher("/ChangePasswordForgot.jsp").forward(request,
								response);
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "homePage":
			try {
				user = (User) session.getAttribute("user");
				directHomePage(request, response, user.getUser_id());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "explorePage":
			try {
				user = (User) session.getAttribute("user");
				userId = user.getUser_id();
				sendDataListFollowerHistory(request, response, userId);
				HashMap<User, String> hashMapExplore = Another_BO.getInstance().listExplore(userId);
				request.setAttribute("hashMapExplore", hashMapExplore);
				getServletContext().getRequestDispatcher("/ExplorePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		case "messagePage":
			user = (User) session.getAttribute("user");
			try {
				sendDataListFollowerHistory(request, response, user.getUser_id());
				getServletContext().getRequestDispatcher("/MessagePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "anotherProfilePage":
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
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
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			try {
				List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
				List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
				List<User> listFollowerr = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
				HashMap<User, String> hashMapp = new HashMap<User, String>();
				for (User follower : listFollowerr) {
					hashMapp.put(follower,
							Follow_BO.getInstance().getDateFollow(follower.getUser_id(), user.getUser_id()));
				}
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
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			try {
				sendDataListFollowerHistory(request, response, userId);
				getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "editProfilePage":
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			try {
				request.setAttribute("user", user);
				sendDataListFollowerHistory(request, response, userId);
				getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "logout":
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			try {
				User_BO.getInstance().changeUserStatus(userId, false);// false: status=offline
				session.removeAttribute("user");
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
		String fullname = lastname + " " + firstname;
		int gender = Integer.parseInt(request.getParameter("gender"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String birthday = request.getParameter("day") + "/" + request.getParameter("month") + "/"
				+ request.getParameter("year");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dBirthday = sdf.parse(birthday);
		User user = new User(1, "user", username, firstname, lastname, fullname, gender, password, email, phone, "",
				dBirthday, "user.jpg", "", "", "", "", "", "", "offline", 0, 0, 0, new Date(), new Date());
		// call add_user() in User_DAO
		User_BO.getInstance().addUser(user);
	}

	// change avatar
	public void changeAvatar(HttpServletRequest request, HttpServletResponse response, int userId) throws Exception {
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
	public void editUser(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		String fullName = lastName + " " + firstName;
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
		user.setFull_name(fullName);
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

	// direct to homepage
	public void directHomePage(HttpServletRequest request, HttpServletResponse response, int userId) throws Exception {
		// hashMap: list post photo and user when direct to home page
		HashMap<Post_Photo, User> hashMapPostPhoto_User = Another_BO.getInstance().listPostPhotoOfFollowing(userId);
		request.setAttribute("hashMapPostPhoto_User", hashMapPostPhoto_User);
		List<User> listUserFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
		request.setAttribute("listUserFollowing", listUserFollowing);
		// list top explore
		List<User> listTopExplore = Another_BO.getInstance().listTopExplore(userId);
		request.setAttribute("listTopExplore", listTopExplore);
		User_BO.getInstance().changeUserStatus(userId, true); // true: status=online
		sendDataListFollowerHistory(request, response, userId);
		getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
	}

	// send data list follower history to form notification
	public void sendDataListFollowerHistory(HttpServletRequest request, HttpServletResponse response, int userId)
			throws Exception {
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
