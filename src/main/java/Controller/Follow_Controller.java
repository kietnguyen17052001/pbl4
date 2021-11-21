package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.BO.Another_BO;
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getUser_id();
		int targetId = Integer.parseInt(request.getParameter("targetId"));
		try {
			// add new follow
			if (type.equals("follow")) {
				Follow_BO.getInstance().addNewFollow(userId, targetId);
			} else if (type.equals("unfollow") || type.equals("delete")) { // unfollow
				if (type.equals("unfollow")) {
					Follow_BO.getInstance().unFollow(userId, targetId);
				} else {
					Follow_BO.getInstance().unFollow(targetId, userId);
				}
			}
			try {
				List<Post_Photo> listPost = null;
				List<User> listFollowing = null;
				List<User> listFollower = null;
				user = User_BO.getInstance().getUserById(userId); // anotherUser
				session.setAttribute("user", user);
				if (pageFollow.equals("anotherProfilePage")) {
					int anotherUserId = Integer.parseInt(request.getParameter("anotherUserId"));
					User anotherUser = User_BO.getInstance().getUserById(anotherUserId);
					listPost = Post_Photo_BO.getInstance().listPost(anotherUserId);
					listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(anotherUserId, true);
					listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(anotherUserId, false);
					HashMap<User, Boolean> hashMapListFollowingOfAnotherUser = hashMap(listFollowing, userId, false);
					HashMap<User, Boolean> hashMapListFollowerOfAnotherUser = hashMap(listFollower, userId, false);
					boolean isFollowed = Follow_BO.getInstance().isFollowed(userId, anotherUserId); // check user is
																									// following
					request.setAttribute("anotherUser", anotherUser);
					request.setAttribute("isFollowed", isFollowed);
					request.setAttribute("listPost", listPost);
					request.setAttribute("hashMapListFollowing", hashMapListFollowingOfAnotherUser);
					request.setAttribute("hashMapListFollower", hashMapListFollowerOfAnotherUser);
					// send data list follower history
					sendDataListFollower(request, response, user.getUser_id());
					getServletContext().getRequestDispatcher("/AnotherProfilePage.jsp").forward(request, response);
				} else if (pageFollow.equals("homePage")) {
					HashMap<Post_Photo, User> hashMapPostPhoto_User = Another_BO.getInstance()
							.listPostPhotoOfFollowing(userId);
					request.setAttribute("hashMapPostPhoto_User", hashMapPostPhoto_User);
					List<User> listUserFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId,
							true);
					request.setAttribute("listUserFollowing", listUserFollowing);
					// list top explore
					List<User> listTopExplore = Another_BO.getInstance().listTopExplore(userId);
					request.setAttribute("listTopExplore", listTopExplore);
					User_BO.getInstance().changeUserStatus(userId, true); // true: status=online
					// send data list follower history
					sendDataListFollower(request, response, userId);
					getServletContext().getRequestDispatcher("/HomePage.jsp").forward(request, response);
				} else {
					listPost = Post_Photo_BO.getInstance().listPost(userId);
					listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
					user = User_BO.getInstance().getUserById(userId); // anotherUser
					session.setAttribute("user", user);
					request.setAttribute("listFollowing", listFollowing);
					request.setAttribute("listPost", listPost);
					sendDataListFollower(request, response, userId);
					getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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

	// send data
	public void sendDataListFollower(HttpServletRequest request, HttpServletResponse response, int userId)
			throws Exception {
		List<User> listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
		HashMap<User, String> hashMap = new HashMap<User, String>();
		for (User follower : listFollower) {
			hashMap.put(follower, Follow_BO.getInstance().getDateFollow(follower.getUser_id(), userId));
		}
		request.setAttribute("listFollower", listFollower);
		request.setAttribute("hashMap", hashMap);
	}
}
