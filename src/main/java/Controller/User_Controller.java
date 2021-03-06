package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.cj.Session;

import Model.BEAN.Message;
import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.BO.Another_BO;
import Model.BO.Conversation_BO;
import Model.BO.Follow_BO;
import Model.BO.Message_BO;
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
			String contentSearch = request.getParameter("contentSearch").trim();
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			try {
				LinkedHashMap<User, String> listUserSearch = Another_BO.getInstance().listUserSearch(userId,
						contentSearch);
				sendDataListFollowerHistory(request, response, userId);
				request.setAttribute("contentSearch", contentSearch);
				request.setAttribute("listUserSearch", listUserSearch);
				getServletContext().getRequestDispatcher("/ResultSearchPage.jsp").forward(request, response);
			} catch (Exception e) {
				try {
					directHomePage(request, response, user.getUser_id());
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
			break;
		case "searchAjax":
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			String name = request.getParameter("contentSearch").trim();
			PrintWriter out = response.getWriter();
			try {
				List<User> listUser = Another_BO.getInstance().listUserByName(name);
				if (name == "" || listUser.size() == 0) {
					out.println("<div style=\"text-align:center\">No result</div>");
				} else {
					String link;
					for (User u : listUser) {
						link = (userId == u.getUser_id()) ? "User_Controller?type=profilePage"
								: "User_Controller?type=anotherProfilePage&anotherUserId=" + u.getUser_id();
						out.println("<div class=\"box-load-user\">");
						out.println("<div>");
						out.println("<a href=\"" + link + "\"><img src=\"image/" + u.getPhoto()
								+ "\" alt=\"avatar\" width=\"40\" height=\"40\"></a>");
						out.println("</div>");
						out.println("<div>");
						out.println("<a href=\"" + link + "\"><strong>" + u.getFull_name() + "</strong></a>");
						out.println("</div>");
						out.println("</div>");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
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
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username != null && password != null) {
				try {
					if (User_BO.getInstance().isExistUser(username, password)) {
						user = User_BO.getInstance().getUser(username, password);
//						Socket socket = new Socket("localhost", 6969);
//						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//						String msg = "name-" + user.getFull_name() + ", id-" + user.getUser_id();
//						dos.writeUTF(msg);
//						socket.close();
						// create and push user up session
						session.setAttribute("user", user);
						userId = user.getUser_id();
						// cookie
						Cookie cookieUsername = new Cookie("username", username);
						Cookie cookiePassword = new Cookie("password", password);
						cookieUsername.setMaxAge(60 * 60 * 24);
						cookiePassword.setMaxAge(60 * 60 * 24);
						// save cookie
						response.addCookie(cookieUsername);
						response.addCookie(cookiePassword);
						directHomePage(request, response, userId);
					} else {
						getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
					}
				} catch (Exception e) {
				}
			} else {
				try {
					user = (User) session.getAttribute("user");
					directHomePage(request, response, user.getUser_id());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
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
				List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(user.getUser_id(),
						true);
				request.setAttribute("listFollowing", listFollowing);
				request.setAttribute("flag", "f");
				getServletContext().getRequestDispatcher("/MessagePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "chatWithUser":
			user = (User) session.getAttribute("user");
			userId = user.getUser_id();
			int targetId = Integer.parseInt(request.getParameter("targetId"));
			session.setAttribute("targetId", targetId);
			try {
				User target = User_BO.getInstance().getUserById(targetId);
				if (Conversation_BO.getInstance().isExistConversation(userId, targetId)) {
					int conversationId = Conversation_BO.getInstance().getConversationId(userId, targetId);
					List<Message> listMessage = Message_BO.getInstance().listMessageInConversation(conversationId);
					request.setAttribute("listMessage", listMessage);
				} else {
					List<Message> listMessage = new ArrayList<Message>();
					listMessage.add(new Message(-1, -1, userId, targetId, new Date(),
							"Hay gui loi chao den " + target.getFull_name()));
					request.setAttribute("listMessage", listMessage);
				}
				// get list message from conversation by conversation id
				sendDataListFollowerHistory(request, response, user.getUser_id());
				List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(user.getUser_id(),
						true);
				request.setAttribute("listFollowing", listFollowing);
				request.setAttribute("flag", "t");
				request.setAttribute("target", target);
				getServletContext().getRequestDispatcher("/MessagePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "changeNewFollowerNumber":
			user = (User) session.getAttribute("user");
			try {
				User_BO.getInstance().resetNewFollower(user.getUser_id());
			} catch (Exception e) {
				System.out.println(e.getMessage() + " line 223");
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
				HashMap<User, Integer> hashMapListFollowingOfAnotherUser = hashMap(listFollowingOfAnotherUser, userId);
				HashMap<User, Integer> hashMapListFollowerOfAnotherUser = hashMap(listFollowerOfAnotherUser, userId);
				boolean isFollowing = Follow_BO.getInstance().isFollowed(userId, anotherUserId); // check user is
																									// following
																									// another
				boolean isFollowed = Follow_BO.getInstance().isFollowed(anotherUserId, userId); // check another is
																								// following user
				sendDataListFollowerHistory(request, response, userId);
				String followingOfAnother = Another_BO.getInstance().followingOfAnother(userId, anotherUserId);
				request.setAttribute("anotherUser", anotherUser);
				request.setAttribute("isFollowing", isFollowing);
				request.setAttribute("isFollowed", isFollowed);
				request.setAttribute("followingOfAnother", followingOfAnother);
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
				user = User_BO.getInstance().getUserById(userId);
			} catch (Exception e) {
			}
			session.setAttribute("user", user);
			try {
				List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
				List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
				List<User> listFollowerr = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
				LinkedHashMap<User, String> linkedHashMap = new LinkedHashMap<User, String>();
				for (User follower : listFollowerr) {
					linkedHashMap.put(follower,
							Follow_BO.getInstance().getDateFollow(follower.getUser_id(), user.getUser_id()));
				}
				request.setAttribute("listFollowing", listFollowing);
				request.setAttribute("listFollower", listFollowerr);
				request.setAttribute("listPost", listPost);
				request.setAttribute("linkedHashMap", linkedHashMap);
				request.setAttribute("newFollower", User_DAO.getInstance().getUserById(userId).getNewFollower());
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
				// get username and password from cookie
				Cookie arrCookie[] = request.getCookies();
				for (Cookie cookie : arrCookie) {
					if (cookie.getName().equals("username")) {
						request.setAttribute("username", cookie.getValue());
					}
					if (cookie.getName().equals("password")) {
						request.setAttribute("password", cookie.getValue());
					}
				}
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
		String username = request.getParameter("username").trim();
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		String fullname = lastname + " " + firstname;
		int gender = Integer.parseInt(request.getParameter("gender"));
		String email = request.getParameter("email").trim();
		String phone = request.getParameter("phone").trim();
		String password = request.getParameter("password").trim();
		String birthday = request.getParameter("day") + "/" + request.getParameter("month") + "/"
				+ request.getParameter("year");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dBirthday = sdf.parse(birthday);
		User user = new User(1, "user", username, firstname, lastname, fullname, gender, password, email, phone, "",
				dBirthday, "user.jpg", "", "", "", "", "", "", "offline", 0, 0, 0, 0, new Date(), new Date());
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
		String lastName = request.getParameter("lastname").trim();
		String firstName = request.getParameter("firstname").trim();
		String fullName = lastName + " " + firstName;
		int gender = Integer.parseInt(request.getParameter("gender"));
		String email = request.getParameter("email").trim();
		String phone = request.getParameter("phone").trim();
		String city = request.getParameter("city").trim();
		String about = request.getParameter("about").trim();
		String job = request.getParameter("job").trim();
		String company = request.getParameter("company").trim();
		String facebook = request.getParameter("facebook").trim();
		String instagram = request.getParameter("instagram").trim();
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
		LinkedHashMap<Post_Photo, User> linkedHashMapPost = Another_BO.getInstance().listPostPhotoOfFollowing(userId);
		request.setAttribute("linkedHashMapPost", linkedHashMapPost);
		// LinkedHashMap: follow each other(user and another)
		LinkedHashMap<User, Boolean> listUserFollowing = Follow_BO.getInstance().listFollowing(userId);
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
		LinkedHashMap<User, String> linkedHashMap = new LinkedHashMap<User, String>();
		for (User follower : listFollower) {
			linkedHashMap.put(follower, Follow_BO.getInstance().getDateFollow(follower.getUser_id(), userId));
		}
		request.setAttribute("linkedHashMap", linkedHashMap);
		// get new follower number of user
		request.setAttribute("newFollower", User_BO.getInstance().getUserById(userId).getNewFollower());
		// System.out.println(User_DAO.getInstance().getUserById(userId).getNewFollower());
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
	public HashMap<User, Integer> hashMap(List<User> listUser, int userId) throws Exception {
		HashMap<User, Integer> hashMap = new HashMap<User, Integer>();
		for (User u : listUser) {
			if (u.getUser_id() == userId) {
				hashMap.put(u, 3); // is user
				continue;
			}
			if (Follow_BO.getInstance().isFollowed(userId, u.getUser_id())) {
				hashMap.put(u, 1); // user is following target
			} else {
				hashMap.put(u, 0); // user isn't following target
			}
		}
		return hashMap;
	}
}
