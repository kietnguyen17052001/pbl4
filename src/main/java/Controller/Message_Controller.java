package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Message;
import Model.BEAN.User;
import Model.BO.Conversation_BO;
import Model.BO.Message_BO;
import Model.BO.User_BO;

/**
 * Servlet implementation class Message_Controller
 */
@WebServlet("/Message_Controller")
public class Message_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Message_Controller() {
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
		switch (type) {
		case "chatBox":
			int userId = Integer.parseInt(request.getParameter("userId"));
			int targetId = Integer.parseInt(request.getParameter("targetId"));
			try {
				User target = User_BO.getInstance().getUserById(targetId);
				int conversationId = Conversation_BO.getInstance().getConversationId(userId, targetId);
				PrintWriter out = response.getWriter();
				out.println("<div class=\"right-mess-header\">");
				out.println("<div class=\"following-one\">");
				out.println("<img class=\"img-avatar-mess\" alt=\"avatar\" src=\"image/" + target.getPhoto() + "\">");
				out.println("<h4 class=\"name-mess\">" + target.getFull_name() + "</h4>");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"box-chat\">");
				out.println("<div class=\"container-chat\">");
				List<Message> list = Message_BO.getInstance().listMessageInConversation(conversationId);
				for (Message message : list) {
					if (message.getSender_id() == userId) {
						out.println("<div class=\"message-box sender\">");
						out.println("<div></div>");
						out.println("<div class=\"message-sender\">" + message.getContent() + "</div>");
						out.println("</div>");
					} else {
						out.println("<div class=\"message-box receiver\">");
						out.println("<div><img src=\"image/" + target.getPhoto()
								+ "\" alt=\"avatar\" width=\"40\" height=\"40\" style=\"border-radius:50%;\"></div>");
						out.println("<div class=\"message-receiver\">" + message.getContent() + "</div>");
						out.println("</div>");
					}
				}
				out.println("<div class=\"input-chat\">");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
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
