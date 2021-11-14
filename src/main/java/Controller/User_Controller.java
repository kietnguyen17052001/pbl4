package Controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.User_BO;
import Model.DAO.User_DAO;

/**
 * Servlet implementation class User_Controller
 */
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
		case "login":
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
		case "create":
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
		case "edit":
			try {
				editUser(request, response);
				getServletContext().getRequestDispatcher("/EditProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
			}
			break;
		case "changePassword":
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
				request.setAttribute("user", user);
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
				new byte[2048], "", "", "", "", "", "", "offline", 0, 0, 0, new Date(), new Date());
		// call add_user() in User_DAO
		User_BO.getInstance().addUser(user);
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
