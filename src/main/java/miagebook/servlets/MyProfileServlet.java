package miagebook.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Profile;
import beans.UserProfile;

public class MyProfileServlet extends AbstractServlet{
	
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserProfile user = (UserProfile) request.getSession().getAttribute("user");
			if(user != null) {
				// TEST
				List<Profile> friends = new ArrayList<>();
				for(int i = 0 ; i < 10 ; i++) {
					Profile p = new Profile();
					p.setEmail("assasa@asasa.com");
					p.setFirstName("Assssim");p.setLastName("senoussi");
					p.setLogin("asssimtsenoussi");
					friends.add(p);
				}
				//FIN TEST
				request.setAttribute("friends", friends);
				forwardTo(request,response,"/myprofile.jsp");
			}
			else
				response.sendRedirect("login");
	 }
	 
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	 }

}
