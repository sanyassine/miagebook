package miagebook.servlets;
   
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import beans.UserProfile;
import services.RegisterService;

@Path("login")
public class RegisterServlet extends AbstractServlet {
    private static final long serialVersionUID = -4751096228274971485L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	if(request.getSession().getAttribute("user") != null)
    		//forwardTo(request,response,"/home");
    		response.sendRedirect("home");
    	else
    		forwardTo(request, response, "/inscription.jsp");// methode ecrite dans AbstractServlet pour simplfier
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		String login = request.getParameter("login");
    	String password = request.getParameter("password");
    	String firstName = request.getParameter("firstname");
    	String lastName = request.getParameter("lastname");
    	String email = request.getParameter("email");
    	
    	UserProfile user = RegisterService.register(login, password, firstName, lastName, email);
    	setUserInSession(request, user);
    	List<String> errorMessages = RegisterService.checkData(login,password,firstName,lastName,email);
    	// verifier en base si l'email/login existe deja ou non
    	if(errorMessages.size() != 0) {
    		request.getSession().setAttribute("error_message", errorMessages);
    		forwardTo(request,response,"/inscription.jsp");
    	}else if(profileMapper.insert(user)){
    		profileMapper.connectUser(user); //to see that user is connected in db
    		forwardTo(request,response,"/home");
    	}else {
    		errorMessages.add("insertion error");
    		request.getSession().setAttribute("error_message", errorMessages);
    		forwardTo(request,response,"/inscription.jsp");
    	}
    }
}
