package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserProfile;
import services.CommentService;

public class FeedServlet extends AbstractServlet {

	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserProfile user = getUserFromSession(request);
			if(user != null) { 
				String login = request.getParameter("login");
				request.setAttribute("login", login);
				forwardTo(request,response,"/feed.jsp");
			}
			else {
				response.sendRedirect("login");
			}
 }
	 
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserProfile user = getUserFromSession(request);
			String login = request.getParameter("login");
			request.setAttribute("login", login);
			if(user != null) { 
				String contentComment = (String) request.getParameter("contentComment");
				int idPostComment = CommentService.getIdPostComment(request);  
				if(contentComment != null && idPostComment != -1 && contentComment.length() > 0 ) { // new comment added
					CommentService.createComment(user, idPostComment, contentComment);
				}
				forwardTo(request,response,"/feed.jsp");
			}
			else {
				response.sendRedirect("login");
			}
	 }
	
}
