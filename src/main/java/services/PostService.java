package services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import beans.Post;
import beans.Profile;
import persistence.PostMapper;

public class PostService {
	private static PostMapper postMapper = PostMapper.getInstance();
	
	public static void createPost(Profile user, String content, String title) {
		Post post = new Post();
		post.setAuthor(user);
		post.setAuthorLogin(user.getLogin());
		post.setContent(content);
		post.setDate(Calendar.getInstance().getTime());
		post.setTitle(title);
		postMapper.insert(post);
	}
}
