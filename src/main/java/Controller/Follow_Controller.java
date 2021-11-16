package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.BO.Follow_BO;
import Model.BO.Post_Photo_BO;
import Model.BO.User_BO;

/**
 * Servlet implementation class Follow_Controller
 */
@WebServlet("/Follow_Controller")
public class Follow_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Follow_Controller() {
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
		String type = request.getParameter("type");
		int userId;
		int targetId;
		switch (type) {
		case "follow":
			userId = Integer.parseInt(request.getParameter("userId"));
			targetId = Integer.parseInt(request.getParameter("targetId"));
			try {
				Follow_BO.getInstance().addNewFollow(userId, targetId);
				try {
					User user = User_BO.getInstance().getUserById(userId);
					List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
					List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
					List<User> listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
					request.setAttribute("listFollowing", listFollowing);
					request.setAttribute("listFollower", listFollower);
					request.setAttribute("user", user);
					request.setAttribute("listPost", listPost);
					getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
				} catch (Exception e) {
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "unfollow":
			userId = Integer.parseInt(request.getParameter("userId"));
			targetId = Integer.parseInt(request.getParameter("targetId"));
			try {
				Follow_BO.getInstance().unFollow(userId, targetId);
				try {
					User user = User_BO.getInstance().getUserById(userId);
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

}
