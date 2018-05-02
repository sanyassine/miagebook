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
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserProfile userProfile = profileMapper.authenticate(login, password);
		if(userProfile != null) {
			setUserInSession(request, userProfile);
			profileMapper.connectUser(userProfile); //to see that user is connected in db
			forwardTo(request,response,"/home");
		}else {
			forwardTo(request, response, "/login.jsp");
		}
	}
}
