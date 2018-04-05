package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserProfile;

public class LoginServlet extends AbstractServlet {
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
		forwardTo(request, response, "/login.jsp");
	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		UserProfile userProfile = new UserProfile(); // provide from BDD
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		userProfile.setLogin(login);
		userProfile.setPassword(password);
		setUserInSession(request, userProfile);
		forwardTo(request,response,"/home");
	}
}
