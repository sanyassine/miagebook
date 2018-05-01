package beans;

import java.sql.Timestamp;
import java.util.Date;

public class Comment extends Bean{
	private int idComment;
	private Post post;
	private Profile author;
	private String authorLogin;
	private Timestamp date;
	private String title;
	private String content;
	private int idPost;
	
	public String getAuthorLogin() {
		return authorLogin;
	}
	public void setAuthorLogin(String authorLogin) {
		this.authorLogin = authorLogin;
	}
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		updateLastChangeDate();
	}
	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		updateLastChangeDate();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idComment;
		result = prime * result + ((post == null) ? 0 : post.hashCode());
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
		Comment other = (Comment) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idComment != other.idComment)
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
