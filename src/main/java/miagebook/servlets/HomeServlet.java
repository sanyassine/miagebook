package miagebook.servlets;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.Post;
import beans.UserProfile;
import services.CommentService;
import services.PostService;

public class HomeServlet extends AbstractServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile user = getUserFromSession(request);
		if(user != null) { // providing posts from user connected
			List<Post> posts = postMapper.findPostsByLogin(user.getLogin());
			request.setAttribute("posts", posts);
			forwardTo(request,response,"/home.jsp");
		}
		else
			response.sendRedirect("login");
	}
	
	@Override // on login
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserProfile user = getUserFromSession(request);
		String contentPost = (String) request.getParameter("contentPost");
		String titlePost   = (String) request.getParameter("titlePost");
		String contentComment = (String) request.getParameter("contentComment");
//		Part file = request.getPart("imagePost");
//		String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
//		System.out.println(fileName);
		int idPostComment = CommentService.getIdPostComment(request);  
		if(contentPost != null && titlePost != null && contentPost.length() > 0 && titlePost.length() > 0) { // new post added
			if(user != null) {
				PostService.createPost(user, contentPost, titlePost);
			} 
		}else if(contentComment != null && idPostComment != -1 && contentComment.length() > 0 ) { // new comment added
			CommentService.createComment(user, idPostComment, contentComment);
		}
		response.sendRedirect("home");
	}
}
