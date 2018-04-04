package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserProfile;

public class FriendsServlet extends AbstractServlet{
	
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserProfile user = (UserProfile) request.getSession().getAttribute("user");
			if(user != null)
				forwardTo(request,response,"/friends.jsp");
			else
				response.sendRedirect("login");
	 }
	 
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	 }

}
