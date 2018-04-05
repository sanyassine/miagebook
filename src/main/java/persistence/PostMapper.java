package persistence;

import java.util.HashMap;
import java.util.Map;

import beans.Post;
import utils.sync.thread.PostSyncThread;
import utils.sync.thread.SyncObjectThread;

public class PostMapper extends DataMapper{
	private Map<Integer,Post> map = new HashMap<Integer,Post>();
	public Post find(int idPost) {
		if(map.containsKey(idPost)) {
			return map.get(idPost);
		}
		Post post = new Post();
		//provide from database
		
		SyncObjectThread thread = new PostSyncThread(post, this);
		return post;
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
