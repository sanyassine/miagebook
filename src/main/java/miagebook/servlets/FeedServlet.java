package miagebook.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Profile;
import beans.UserProfile;
import services.FriendsService;

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
			if(user != null) { 
				String login = request.getParameter("login");
				request.setAttribute("login", login);
				forwardTo(request,response,"/feed.jsp");
			}
			else {
				response.sendRedirect("login");
			}
	 }
}
