package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends AbstractServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getAttribute("username");
		if(username != null)
			response.sendRedirect("home");//goTo(request,response,"/home");
		else
			response.sendRedirect("login");//goTo(request,response,"/login");
	}

}
