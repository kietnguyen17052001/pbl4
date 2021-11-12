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
		String userId;
		switch (type) {
		case "login":
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				if (User_BO.getInstance().isExistUser(username, password)) {
					userId = User_BO.getInstance().getIdUser(username);
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
				System.out.println(e.getMessage());
			}
			break;
		case "edit":
			break;
		case "home":
			userId = request.getParameter("userId");
			request.setAttribute("userId", userId);
			getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
			break;
		case "message":
			userId = request.getParameter("userId");
			request.setAttribute("userId", userId);
			getServletContext().getRequestDispatcher("/MessagePage.jsp").forward(request, response);
			break;
		case "follow":
			userId = request.getParameter("userId");
			request.setAttribute("userId", userId);
			getServletContext().getRequestDispatcher("/FollowPage.jsp").forward(request, response);
			break;
		case "profile":
			userId = request.getParameter("userId");
			try {
				User user = User_BO.getInstance().getUserById(userId);
				request.setAttribute("user", user);
				getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
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
		if (username == "") {
			username = null;
		}
		if (firstname == "") {
			firstname = null;
		}
		if (lastname == "") {
			lastname = null;
		}
		if (email == "") {
			email = null;
		}
		if (phone == "") {
			phone = null;
		}
		if (password == "") {
			password = null;
		}
		User user = new User(1, "user", username, firstname, lastname, gender, password, email, phone, "", dBirthday,
				new byte[2048], "", "", "", "", "", "", "", 0, 0, 0, new Date(), new Date());
		// call add_user() in User_DAO
		User_BO.getInstance().addUser(user);
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
