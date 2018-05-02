package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserProfile;

public class DisconnectServlet extends AbstractServlet{
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
		UserProfile user = getUserFromSession(request);
		if(user != null) {
			profileMapper.disconnectUser(getUserFromSession(request));
			removeUserInSession(request);
		}
		response.sendRedirect("login");
	}
}
