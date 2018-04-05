package persistence;

import java.util.HashMap;
import java.util.Map;

import beans.Comment;

public class CommentMapper {
	private Map<Integer,Comment> map = new HashMap<Integer,Comment>();
	public Comment find(int idComment) {
		return null;
	}
	public boolean insert(Comment comment) {
		return false;
	}
	public boolean updateContent(Comment comment) {
		return false;
	}
}
