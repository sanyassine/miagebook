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
		String contentPost = (String) request.getParameter("contentPost");
		String titlePost   = (String) request.getParameter("titlePost");
		String contentComment = (String) request.getParameter("contentComment");
		int idPostComment;  
		try {
			idPostComment     = Integer.parseInt(request.getParameter("idpostcomment")) ;
		}catch(NumberFormatException e) {
			idPostComment = -1;
		}
		if(contentPost != null && titlePost != null) {
			UserProfile user = (UserProfile) request.getSession().getAttribute("user");
			if(user != null) {
				Post post = new Post();
				post.setAuthor(user);
				post.setAuthorLogin(user.getLogin());
				post.setContent(contentPost);
				post.setDate(new Timestamp(new Date().getTime()));
				post.setTitle(titlePost);
				PostMapper mapper = new PostMapper();
				mapper.insert(post);
			}
		}else if(contentComment != null && idPostComment != -1) {
			System.out.println("id :"+idPostComment+" content: "+contentComment);
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
