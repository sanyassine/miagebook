package services;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import beans.Comment;
import beans.Profile;
import persistence.CommentMapper;

public class CommentService {
	private static CommentMapper commentMapper = CommentMapper.getInstance();
	public static void createComment(Profile user, int idPost, String content) {
		Comment comment = new Comment();
		comment.setAuthorLogin(user.getLogin());
		comment.setContent(content);
		comment.setIdPost(idPost);
		comment.setDate(new Timestamp(new Date().getTime()));
		commentMapper.insert(comment);
	}
	
	public static int getIdPostComment(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("idpostcomment")) ;
		}catch(NumberFormatException e) {
			return -1;
		}
	}
}
