package beans;

import java.util.Date;

public class Post {
	private UserProfile author;
	private Date date;
	private String content;
	public UserProfile getAuthor() {
		return author;
	}
	public void setAuthor(UserProfile author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
