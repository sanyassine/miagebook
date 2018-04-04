package beans;

import java.util.List;

public class Profile {
	
	private String firstName;
	private String lastName;
	private String email;
	private List<UserProfile> friends;
	private List<Post> posts;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<UserProfile> getFriends() {
		return friends;
	}
	public void setFriends(List<UserProfile> friends) {
		this.friends = friends;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
