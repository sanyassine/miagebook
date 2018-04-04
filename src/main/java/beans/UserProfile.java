package beans;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {

	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private List<UserProfile> friends = new ArrayList<>();
	private List<Post> posts = new ArrayList<>();
	
	public UserProfile() {
	
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
