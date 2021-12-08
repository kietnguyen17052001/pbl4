package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
import Model.BO.Follow_BO;
import Model.BO.Post_Photo_BO;
import Model.BO.User_BO;
import Model.DAO.User_DAO;

/**
 * Servlet implementation class Post_Photo_Controller
 */
@MultipartConfig
@WebServlet("/Post_Photo_Controller")
public class Post_Photo_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Post_Photo_Controller() {
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getUser_id();
		int postId;
		switch (type) {
		case "add":
			try {
				Part file = request.getPart("image");
				String imageFileName = file.getSubmittedFileName();
				String uploadPath = "D:/Dropbox/Season3 - DUT/PBL4/Code/AppPBL4/src/main/webapp/image/" + imageFileName;
				FileOutputStream fos = new FileOutputStream(uploadPath);
				InputStream is = file.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				String content = request.getParameter("photo-content");
				Object createDate = new Date();
				Object updateDate = new Date();
				Post_Photo postPhoto = new Post_Photo(1, userId, content, imageFileName, createDate, updateDate);
				// add new post
				Post_Photo_BO.getInstance().postPhoto(postPhoto);
				// update number of posts
				User_BO.getInstance().updateNumberOfPost(userId, true); // true is up new post, false is delete post
				// reload page when post new photo
				loadPageWhenPostOrDeletePhoto(request, response, userId);
			} catch (Exception e) {
				try {
					loadPageWhenPostOrDeletePhoto(request, response, userId);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
			break;
		case "edit":
			postId = Integer.parseInt(request.getParameter("postId"));
			String newContent = request.getParameter("newContent");
			try {
				Post_Photo_BO.getInstance().editPostPhoto(postId, newContent);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case "delete":
			postId = Integer.parseInt(request.getParameter("postId"));
			try {
				// delete post
				Post_Photo_BO.getInstance().deletePostPhoto(postId);
				// update number of post when delete
				User_BO.getInstance().updateNumberOfPost(userId, false);
				// reload ProfilePage
				loadPageWhenPostOrDeletePhoto(request, response, userId);
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

	public void loadPageWhenPostOrDeletePhoto(HttpServletRequest request, HttpServletResponse response, int userId)
			throws Exception {
		List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
		List<User> listFollowing = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, true);
		List<User> listFollower = Follow_BO.getInstance().listFollowingOrFollowerInProfile(userId, false);
		LinkedHashMap<User, String> linkedHashMap = new LinkedHashMap<User, String>();
		for (User follower : listFollower) {
			linkedHashMap.put(follower, Follow_BO.getInstance().getDateFollow(follower.getUser_id(), userId));
		}
		request.setAttribute("listFollowing", listFollowing);
		request.setAttribute("listFollower", listFollower);
		request.setAttribute("listPost", listPost);
		request.setAttribute("linkedHashMap", linkedHashMap);
		request.setAttribute("newFollower", User_DAO.getInstance().getUserById(userId).getNewFollower());
		HttpSession session = request.getSession();
		User user = User_BO.getInstance().getUserById(userId);
		session.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
	}
}
