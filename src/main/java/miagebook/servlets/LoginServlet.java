package miagebook.servlets;
   
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserProfile;


public class LoginServlet extends AbstractServlet {
    private static final long serialVersionUID = -4751096228274971485L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	goTo(request, response, "/login.jsp");// methode ecrite dans AbstractServlet pour simplfier
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	String login = request.getParameter("login");
    	String password = request.getParameter("password");
    	UserProfile userProfile = new UserProfile(login,password);
    	request.setAttribute("user", userProfile);
    	goTo(request,response,"/connected.jsp");
    }
    
}
