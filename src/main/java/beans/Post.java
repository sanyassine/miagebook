package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Post extends Bean{
	private int idPost ;
	private Profile author;
	private String authorLogin;
	private Timestamp date;
	private String title;
	private String content;
	private List<Comment> comment = new ArrayList<>();
	
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public Profile getAuthor() {
		return author;
	}
	public void setAuthor(Profile author) {
		this.author = author;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		updateLastChangeDate();
	}
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		updateLastChangeDate();
	}
	
	public String getAuthorLogin() {
		return authorLogin;
	}
	public void setAuthorLogin(String authorLogin) {
		this.authorLogin = authorLogin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idPost;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idPost != other.idPost)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Post [idPost=" + idPost + ", author=" + author + ", date=" + date + ", title=" + title + ", content="
				+ content + "]";
	}
	
}
