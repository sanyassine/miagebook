package persistence;

import java.util.HashMap;
import java.util.Map;

import beans.Comment;
import utils.sync.thread.CommentSyncThread;
import utils.sync.thread.SyncObjectThread;

public class CommentMapper extends DataMapper{
	private Map<Integer,Comment> map = new HashMap<Integer,Comment>();
	
	public CommentMapper() {
		super();
	}
	public Comment find(int idComment) {
		if(map.containsKey(idComment)) {
			return map.get(idComment);
		}
		Comment comment = new Comment();
		//provide from database
		
		SyncObjectThread thread = new CommentSyncThread(comment, this);
		return comment;
	}
	public boolean insert(Comment comment) {
		return false;
	}
	public boolean updateContent(Comment comment) {
		return false;
	}
}
