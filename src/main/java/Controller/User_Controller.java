package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.User_DAO;
import Model.User;

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
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		switch (type) {
		case "create":
			try {
				createUser(request, response);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			} catch (Exception e) {
				response.sendRedirect("Registration.jsp");
				System.out.println(e.getMessage());
			}
			break;
		case "login":
			try {
				if (login(request, response)) { // login successful
					request.getRequestDispatcher("Main.jsp").forward(request, response);
				} else {
					response.sendRedirect("Login.jsp");
				}
			} catch (Exception e) {
				response.sendRedirect("Login.jsp");
				System.out.println(e.getMessage());
			}
			break;
		case "update":
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
		User user = new User(1, "user", username, firstname, lastname, gender, password, email, phone, null, dBirthday,
				null, null, null, null, null, null, null, null, 0, 0, new Date(), new Date());
		// call add_user() in User_DAO
		User_DAO.getInstance().add_user(user);
	}

	// login
	public boolean login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username_phone_mail = request.getParameter("username_phone_mail");
		String password = request.getParameter("password");
		// call login() in User_DAO
		return User_DAO.getInstance().login(username_phone_mail, password);
	}
}
