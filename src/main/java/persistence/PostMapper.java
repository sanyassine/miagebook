package persistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import beans.Post;
import utils.sync.thread.PostSyncThread;
import utils.sync.thread.SyncObjectThread;

public class PostMapper extends DataMapper{
	private Map<Integer,Post> map = new HashMap<Integer,Post>();
	
	public PostMapper() {
		super();
	}
	
	CallableStatement providePostsStatement;

	public Post find(int idPost) {
		if (map.containsKey(idPost)) {
			return map.get(idPost);
		}
		Post post = null;
		// provide from database
		try {
			if (providePostsStatement == null) {
				providePostsStatement = c
						.prepareCall("select id_post,login_author,date_post,content,title "
				+ "from posts where id_post=?");
			}
			providePostsStatement.setInt(1, idPost);
			ResultSet rs = providePostsStatement.executeQuery();
			if(rs.next()) {
				post = new Post();
				String loginAuthor = rs.getString("login_author");
				Timestamp time = rs.getTimestamp("date_post");
				String content = rs.getString("content");
				String title = rs.getString("title");
				post.setAuthorLogin(loginAuthor);
				post.setDate(time);
				post.setContent(content);
				post.setIdPost(idPost);
				post.setTitle(title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(post != null) {
			map.put(idPost, post);
			SyncObjectThread thread = new PostSyncThread(post, this); // TO TEST
		}
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
