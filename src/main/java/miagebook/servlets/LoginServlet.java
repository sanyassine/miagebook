package miagebook.servlets;
   
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    	//response.sendRedirect("/login.jsp");
    	forwardTo(request, response, "/login.jsp");// methode ecrite dans AbstractServlet pour simplfier
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	String login = request.getParameter("login");
    	String password = request.getParameter("password");
    	String firstName = request.getParameter("firstname");
    	String lastName = request.getParameter("lastname");
    	UserProfile userProfile = new UserProfile();
    	userProfile.setLogin(login);
    	userProfile.setPassword(password);
    	userProfile.setFirstName(firstName);
    	userProfile.setLastName(lastName);
    	request.getSession().setAttribute("user", userProfile);
    	List<String> errorMessages = checkData(login,password,firstName,lastName);
    	if(errorMessages.size() != 0) {
    		request.getSession().setAttribute("error_message", errorMessages);
    		forwardTo(request,response,"/login.jsp");
    	}else {
    		forwardTo(request,response,"/home");
    	}
    }
    
    private List<String> checkData(String login, String password, String firstname, String lastname) {
    	List<String> errorMessages = new ArrayList<String>();
    	if(!isCorrectLogin(login)) {
    		errorMessages.add("incorrect login\n");
    	}if(!isCorrectPassword(password)) {
    		errorMessages.add("incorrect password\n");
    	}if(!isCorrectFirstName(firstname)) {
    		errorMessages.add("incorrect first name\n");
    	}if(!isCorrectLastName(lastname)) {
    		errorMessages.add("incorrect last name");
    	}
    	return errorMessages;
    }
    
    private boolean isCorrectFirstName(String firstname) {
    	if(firstname.length() != 0)
    		return true;
    	return false;
    }
    
    private boolean isCorrectLastName(String lastname) {
    	if(lastname.length() != 0)
    		return true;
    	return false;
    }
    
    private boolean isCorrectLogin(String login) {
    	if(login.length() > 0)
    		return true;
    	return false;
    }
    
    
    private boolean isCorrectPassword(String password) {
    	if(password.length() > 0)
    		return true;
    	return false;
    }
}
