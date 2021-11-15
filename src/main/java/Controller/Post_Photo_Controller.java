package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.BEAN.Post_Photo;
import Model.BEAN.User;
import Model.BO.Post_Photo_BO;
import Model.BO.User_BO;

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
		int userId;
		User user;
		switch (type) {
		case "add":
			userId = Integer.parseInt(request.getParameter("userId"));
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
				Post_Photo_BO.getInstance().postPhoto(postPhoto);
				List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
				user = User_BO.getInstance().getUserById(userId);
				request.setAttribute("user", user);
				request.setAttribute("listPost", listPost);
				getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
			} catch (Exception e) {
				try {
					List<Post_Photo> listPost = Post_Photo_BO.getInstance().listPost(userId);
					user = User_BO.getInstance().getUserById(userId);
					request.setAttribute("user", user);
					request.setAttribute("listPost", listPost);
					getServletContext().getRequestDispatcher("/ProfilePage.jsp").forward(request, response);
				} catch (Exception e1) {
				}
			}
			break;
		case "edit":
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
