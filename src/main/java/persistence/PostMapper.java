package persistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import beans.Comment;
import beans.Post;
import beans.Profile;
import utils.sync.thread.PostSyncThread;
import utils.sync.thread.SyncObjectThread;

public class PostMapper extends DataMapper{
	private static PostMapper INSTANCE;
	public static PostMapper getInstance() {
		if(INSTANCE == null){
			INSTANCE = new PostMapper();
		}
		return INSTANCE;
	}
	private Map<Integer,Post> map = new HashMap<Integer,Post>();
	
	private PostMapper() {
		super();
	}
	
	CallableStatement providePostsStatement;

	public Post find(int idPost) {
		if (map.containsKey(idPost)) {
			Post post = map.get(idPost);
			List<Comment> comments = CommentMapper.getInstance().findCommentsByPost(post);
			post.setComment(comments);
			map.put(idPost, post);
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
				map.put(idPost, post);
				List<Comment> comments = CommentMapper.getInstance().findCommentsByPost(post);
				post.setComment(comments);
				post.setInserted(true);
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
	
	CallableStatement providePostsByLoginStatement;

	public List<Post> findPostsByLogin(String login) {
		List<Post> posts = new ArrayList<Post>();
		try {
			if (providePostsByLoginStatement == null) {

				providePostsByLoginStatement = c.prepareCall(
						"select id_post,login_author,date_post,content,title " 
				+ "from posts where login_author=?");

			}
			providePostsByLoginStatement.setString(1, login);
			ResultSet rs = providePostsByLoginStatement.executeQuery();
			while(rs.next()) {
				String loginAuthor = rs.getString("login_author");
				int idPost = rs.getInt("id_post");
				ProfileMapper profileMapper = ProfileMapper.getInstance();
				Profile profile = profileMapper.find(loginAuthor);
				Post post = find(idPost);
				post.setAuthor(profile);
				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
	
	CallableStatement findPostHomePageStatement;
	public List<Post> findPostHomeByLogin(String login, int n){
		List<Post> posts = new ArrayList<Post>();
		try {
			if(findPostHomePageStatement == null) {
				findPostHomePageStatement = c.prepareCall("select id_post from posts " + 
							"where (login_author in (select login_friend from friends where login_user=?) " + 
							" OR login_author=?) AND ROWNUM <= ? " + 
							" order by DATE_POST");	
			}
			findPostHomePageStatement.setString(1, login);
			findPostHomePageStatement.setString(2, login);
			findPostHomePageStatement.setInt(3, n);
			ResultSet rs = findPostHomePageStatement.executeQuery();
			while(rs.next()) {
				int idPost = rs.getInt("id_post");
				posts.add(find(idPost));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	CallableStatement insertPostStatement;
	public boolean insert(Post post) {
		boolean res;
		try {
			if (insertPostStatement == null) {
				insertPostStatement = c.prepareCall(
						"insert into posts (id_post,login_author,date_post,title,content) values(?,?,?,?,?)");
			}
			int id = new Random().nextInt();
			insertPostStatement.setInt(1, id);
			insertPostStatement.setString(2, post.getAuthorLogin());
			insertPostStatement.setTimestamp(3, post.getDate());
			insertPostStatement.setString(4, post.getTitle());
			insertPostStatement.setString(5, post.getContent());
			
			insertPostStatement.execute();
			c.commit();
			post.setIdPost(id);
			map.put(id, post);
			post.setInserted(true);
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	CallableStatement updateContentStatement;
	public boolean updateContent(Post post) {
		boolean res;
		try {
			if(updateContentStatement == null) {
				updateContentStatement = c.prepareCall("update posts set content=? where id_post=?");
			}
			updateContentStatement.setString(1, post.getContent());
			updateContentStatement.setInt(2, post.getIdPost());
			updateContentStatement.execute();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res =false;
		}
		return res;
	}
	public boolean updateTitle(Post post) {
		return false;
	}
	public boolean delete(Post post) {
		return false;
	}
}
