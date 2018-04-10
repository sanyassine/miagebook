package miagebook.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Post;
import beans.UserProfile;
import persistence.connection.Oracle;

public class HomeServlet extends AbstractServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		if(user != null) {
			// TEST
			Connection c = Oracle.getConnection();
			List<Post> posts = new ArrayList<>();
			for(int i = 0 ; i < 10 ; i++) {
				Post p = new Post();
				p.setContent(c.toString());
				p.setDate(new Date());
				p.setTitle("TiTLEE");
				posts.add(p);
			}
			request.setAttribute("posts", posts);
			forwardTo(request,response,"/home.jsp");
		}
		else
			response.sendRedirect("login");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("home");
	}

}
