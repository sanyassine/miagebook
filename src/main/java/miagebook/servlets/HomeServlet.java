package miagebook.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Comment;
import beans.Post;
import beans.UserProfile;
import persistence.CommentMapper;
import persistence.PostMapper;

public class HomeServlet extends AbstractServlet {
	private PostMapper postMapper       = new PostMapper();
	private CommentMapper commentMapper = new CommentMapper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile user = getUserFromSession(request);
		if(user != null) {
			PostMapper mapper = new PostMapper();
			List<Post> posts = mapper.findPostsByLogin(user.getLogin());
			Map<Post, List<Comment>> commentsByPost = new HashMap<>();
			for(Post post : posts) {
				commentsByPost.put(post, commentMapper.findCommentsByPost(post));
			}
			request.setAttribute("posts", posts);
			request.setAttribute("commentsByPost", commentsByPost);
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
		int idPostComment = getIdPostComment(request);  
		if(contentPost != null && titlePost != null && contentPost.length() > 0 && titlePost.length() > 0) { // new post added
			if(user != null) {
				Post post = new Post();
				post.setAuthor(user);
				post.setAuthorLogin(user.getLogin());
				post.setContent(contentPost);
				post.setDate(new Timestamp(new Date().getTime()));
				post.setTitle(titlePost);
				postMapper.insert(post);
			} 
		}else if(contentComment != null && idPostComment != -1 && contentComment.length() > 0 ) { // new comment added
			commentMapper = new CommentMapper();
			Comment comment = new Comment();
			comment.setAuthorLogin(user.getLogin());
			comment.setContent(contentComment);
			comment.setIdPost(idPostComment);
			comment.setDate(new Date());
			commentMapper.insert(comment);
		}
		response.sendRedirect("home");
		
	}
	
	@Override // add post
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserProfile user = getUserFromSession(request);
		if(user != null) {
			String content = (String) request.getAttribute("contentPost");
			String title   = (String) request.getAttribute("titlePost");
			Post post = new Post();
			post.setAuthor(user);
			post.setAuthorLogin(user.getLogin());
			post.setContent(content);
			post.setDate(new Timestamp(new Date().getTime()));
			post.setTitle(title);
			postMapper = new PostMapper();
			postMapper.insert(post);
			forwardTo(request,response,"/home.jsp");
		}
		else
			response.sendRedirect("login");
	}
	
	private int getIdPostComment(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("idpostcomment")) ;
		}catch(NumberFormatException e) {
			return -1;
		}
	}

}
