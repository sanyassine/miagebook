package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisconnectServlet extends AbstractServlet{
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
		profileMapper.disconnectUser(getUserFromSession(request));
		removeUserInSession(request);
		response.sendRedirect("login");
	}
}
