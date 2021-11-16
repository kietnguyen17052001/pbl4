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
		String pageFollow = request.getParameter("pageFollow");
		getAndSendData(request, response, type, pageFollow);
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

	// send data and open profile page
	public void getAndSendData(HttpServletRequest request, HttpServletResponse response, String type,
			String pageFollow) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int targetId = Integer.parseInt(request.getParameter("targetId"));
		try {
			// add new follow
			if (type.equals("follow")) {
				Follow_BO.getInstance().addNewFollow(userId, targetId);
			} else if (type.equals("unfollow") || type.equals("delete")) { // unfollow
				Follow_BO.getInstance().unFollow(userId, targetId);
			}
			try {
				if (type.equals("delete")) {
					userId = targetId;
				}

				List<Post_Photo> listPost = null;
				List<User> listFollowing = null;
				List<User> listFollower = null;

				if (pageFollow.equals("anotherProfilePage")) {
					int anotherUserId = Integer.parseInt(request.getParameter("anotherUserId"));
					User anotherUser = User_BO.getInstance().getUserById(anotherUserId);
					listPost = Post_Photo_BO.getInstance().listPost(anotherUserId);
					listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(anotherUserId, true);
					listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(anotherUserId, false);
					boolean isFollowed = Follow_BO.getInstance().isFollowed(userId, anotherUserId); // check user is
																									// following
																									// anotherUser
					request.setAttribute("userId", userId);
					request.setAttribute("anotherUser", anotherUser);
					request.setAttribute("isFollowed", isFollowed);
					request.setAttribute("listFollowing", listFollowing);
					request.setAttribute("listFollower", listFollower);
					request.setAttribute("listPost", listPost);
					getServletContext().getRequestDispatcher("/AnotherProfilePage.jsp").forward(request, response);
				} else {
					User user = User_BO.getInstance().getUserById(userId);
					listPost = Post_Photo_BO.getInstance().listPost(userId);
					listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
					listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
					request.setAttribute("user", user);
					request.setAttribute("listFollowing", listFollowing);
					request.setAttribute("listFollower", listFollower);
					request.setAttribute("listPost", listPost);
					getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
