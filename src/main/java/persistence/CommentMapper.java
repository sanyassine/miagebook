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
import utils.sync.thread.CommentSyncThread;
import utils.sync.thread.SyncObjectThread;

public class CommentMapper extends DataMapper{
	private static CommentMapper INSTANCE;
	public static CommentMapper getInstance() {
		if(INSTANCE == null){
			INSTANCE = new CommentMapper();
		}
		return INSTANCE;
	}
	private Map<Integer,Comment> map = new HashMap<Integer,Comment>();
	
	private CommentMapper() {
		super();
	}
	CallableStatement provideCommentStatement;
	public Comment find(int idComment) {
		if(map.containsKey(idComment)) {
			return map.get(idComment);
		}
		Comment comment = new Comment();
		try {
			if(provideCommentStatement == null) {
				provideCommentStatement = c.prepareCall("select id_comments,id_post,author_login,content,date_comment from comments where id_comments=?");
			}
			provideCommentStatement.setInt(1, idComment);
			ResultSet rs = provideCommentStatement.executeQuery();
			if(rs.next()){
				int idPost = rs.getInt("id_post");
				String authorLogin = rs.getString("author_login");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date_comment");
				comment.setIdPost(idPost);
				comment.setAuthorLogin(authorLogin);
				comment.setContent(content);
				comment.setIdComment(idComment);
				comment.setDate(date);
				map.put(idComment, comment);
				comment.setInserted(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//provide from database
		
		SyncObjectThread thread = new CommentSyncThread(comment, this);
		return comment;
	}
	
	CallableStatement provideCommentsByPostStatement;
	public List<Comment> findCommentsByPost(Post post){
		List<Comment> comments = new ArrayList<>();
		try {
			if(provideCommentsByPostStatement == null) {
				provideCommentsByPostStatement = c.prepareCall("select id_comments from comments where id_post=?");
			}
			provideCommentsByPostStatement.setInt(1, post.getIdPost());
			ResultSet rs = provideCommentsByPostStatement.executeQuery();
			while(rs.next()) {
				int idComment = rs.getInt("id_comments");
				Comment com = find(idComment);
				if(com != null) {
					comments.add(com);
				}
			}
			post.setComment(comments);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	CallableStatement insertCommentStatement;
	public boolean insert(Comment comment) {
		boolean res;
		try {
			if(insertCommentStatement == null) {
				insertCommentStatement = c.prepareCall("insert into comments(id_comments,id_post,author_login,content,date_comment)"
						+ " values(?,?,?,?,?)");
				
			}
			int idComment = new Random().nextInt();
			comment.setIdComment(idComment);
			int idPost    = comment.getIdPost();
			String authorLogin = comment.getAuthorLogin();
			String content = comment.getContent();
			insertCommentStatement.setInt(1, idComment);
			insertCommentStatement.setInt(2, idPost);
			insertCommentStatement.setString(3, authorLogin);
			insertCommentStatement.setString(4, content);
			insertCommentStatement.setTimestamp(5, comment.getDate());
			
			insertCommentStatement.execute();
			c.commit();
			map.put(idComment, comment);
			comment.setInserted(true);
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	CallableStatement updateContentStatement;
	public boolean updateContent(Comment comment) {
		boolean res;
		try {
			if (updateContentStatement == null) {
				updateContentStatement = c.prepareCall("update comments set content=? where id_comments=?");
			}
			updateContentStatement.setString(1, comment.getContent());
			updateContentStatement.setInt(2, comment.getIdComment());
			updateContentStatement.execute();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
}
