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
					getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
				} else {
					getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
				}
			} catch (Exception e) {
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
			}
			break;
		case "edit": // edit profile
			try {
				changeAvatar(request, response);
				editUser(request, response);
				getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				try {
					editUser(request, response);
					getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
				} catch (Exception e1) {
				}
			}
			break;
		case "search":
			userId = Integer.parseInt(request.getParameter("userId"));
			String contentSearch = request.getParameter("contentSearch");
			try {
				List<User> listUser = User_BO.getInstance().listUserSearch(contentSearch);
				HashMap<User, Boolean> hashMap = new HashMap<User, Boolean>();
				for (User u : listUser) {
					// remove user has id == userId
					if (u.getUser_id() == userId) {
						listUser.remove(u);
					}
					if (Follow_BO.getInstance().isFollowed(userId, u.getUser_id())) {
						hashMap.put(u, true); // user is following target
					} else {
						hashMap.put(u, false); // user isn't following target
					}
				}
				request.setAttribute("userId", userId);
				request.setAttribute("hashMap", hashMap);
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
					getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
				} else {
					request.setAttribute("user", user);
					getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
				}
			} catch (Exception e) {
			}
			break;
		case "homePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("userId", userId);
			getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
			break;
		case "messagePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("userId", userId);
			getServletContext().getRequestDispatcher("/MessagePage.jsp").forward(request, response);
			break;
		case "followPage":
			userId = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("userId", userId);
			getServletContext().getRequestDispatcher("/FollowPage.jsp").forward(request, response);
			break;
		case "profilePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				user = User_BO.getInstance().getUserById(userId);
				List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
				List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
				List<User> listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
				request.setAttribute("user", user);
				request.setAttribute("listFollowing", listFollowing);
				request.setAttribute("listFollower", listFollower);
				request.setAttribute("listPost", listPost);
				getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
			}
			break;
		case "changePasswordPage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				user = User_BO.getInstance().getUserById(userId);
				request.setAttribute("user", user);
				getServletContext().getRequestDispatcher("/ChangePasswordPage.jsp").forward(request, response);
			} catch (Exception e) {
			}
			break;
		case "editProfilePage":
			userId = Integer.parseInt(request.getParameter("userId"));
			try {
				user = User_BO.getInstance().getUserById(userId);
				request.setAttribute("user", user);
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
	public void editUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int userId = Integer.parseInt(request.getParameter("userId"));
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
}
