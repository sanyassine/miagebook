package services;

import java.util.Date;

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
		comment.setDate(new Date());
		commentMapper.insert(comment);
	}
}
