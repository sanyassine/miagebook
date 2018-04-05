package persistence;

import java.util.HashMap;
import java.util.Map;

import beans.Post;

public class PostMapper {
	private Map<Integer,Post> map = new HashMap<Integer,Post>();
	public Post find(int idPost) {
		return null;
	}
	public boolean insert(Post post) {
		return false;
	}
	public boolean updateContent(Post post) {
		return false;
	}
	public boolean updateTitle(Post post) {
		return false;
	}
	public boolean delete(Post post) {
		return false;
	}
}
