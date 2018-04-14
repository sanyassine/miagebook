package miagebook.servlets;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Profile;
import beans.UserProfile;
import persistence.ProfileMapper;
import persistence.connection.Oracle;

public class LoginServlet extends AbstractServlet {
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
		forwardTo(request, response, "/login.jsp");
	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserProfile userProfile = profileMapper.authentificate(login, password);
		if(userProfile != null) {
			setUserInSession(request, userProfile);
			forwardTo(request,response,"/home");
		}else {
			forwardTo(request, response, "/login.jsp");
		}
	}
}
