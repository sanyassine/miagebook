package miagebook.servlets;
   
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import beans.UserProfile;

@Path("login")
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
    	request.setAttribute("username", userProfile.getLogin());
    	if(login.length() > 0 && password.length() > 0) {
    		//goTo(request,response,"/connected.jsp");
    		response.sendRedirect("home");
    	}else {
    		//goTo(request,response,"/login.jsp");
    		response.sendRedirect("login");
    	}
    }
    
    @Path("/connected")
    protected void onConnect(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	goTo(request, response, "connected.jsp");
    }
    
}
