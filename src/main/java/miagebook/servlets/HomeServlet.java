package miagebook.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Post;
import beans.UserProfile;
import persistence.PostMapper;

public class HomeServlet extends AbstractServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		if(user != null) {
			PostMapper mapper = new PostMapper();
			List<Post> posts = mapper.findPostsByLogin(user.getLogin());
			request.setAttribute("posts", posts);
			forwardTo(request,response,"/home.jsp");
		}
		else
			response.sendRedirect("login");
	}
	
	@Override // on login
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String content = (String) request.getParameter("contentPost");
		String title   = (String) request.getParameter("titlePost");
		if(content != null && title != null) {
			UserProfile user = (UserProfile) request.getSession().getAttribute("user");
			if(user != null) {
				Post post = new Post();
				post.setAuthor(user);
				post.setAuthorLogin(user.getLogin());
				post.setContent(content);
				post.setDate(new Timestamp(new Date().getTime()));
				post.setTitle(title);
				PostMapper mapper = new PostMapper();
				mapper.insert(post);
			}
		}
		response.sendRedirect("home");
	}
	
	@Override // add post
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserProfile user = (UserProfile) request.getSession().getAttribute("user");
		if(user != null) {
			String content = (String) request.getAttribute("contentPost");
			String title   = (String) request.getAttribute("titlePost");
			Post post = new Post();
			post.setAuthor(user);
			post.setAuthorLogin(user.getLogin());
			post.setContent(content);
			post.setDate(new Timestamp(new Date().getTime()));
			post.setTitle(title);
			PostMapper mapper = new PostMapper();
			mapper.insert(post);
			forwardTo(request,response,"/home.jsp");
		}
		else
			response.sendRedirect("login");
	}

}
