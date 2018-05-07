package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Profile extends Bean{
	
	private String firstName;
	private String lastName;
	private String email;
	private String login;
	private List<Profile> friends = new ArrayList<>();
	private List<Post> posts = new ArrayList<>();
	private Timestamp lastConnection;
	private boolean isConnected;
	
	public boolean isFriendsWith(String login) {
		for(Profile friend : friends) {
			if(friend.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
		updateLastChangeDate();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		updateLastChangeDate();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
		updateLastChangeDate();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		updateLastChangeDate();
	}
	public List<Profile> getFriends() {
		return friends;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	public void setFriends(List<Profile> friends) {
		this.friends = friends;
	}
	public void removeFriendByLogin(String login) {
		Profile profileToRemove = null;
		for(Profile friend : friends) {
			if(friend.getLogin().equals(login)) {
				profileToRemove = friend;
			}
		}
		friends.remove(profileToRemove);
	}
	
	public void addFriendByLogin(String login) {
		Profile profileToAdd = null;
		for(Profile friend : friends) {
			if(friend.getLogin().equals(login)) {
				profileToAdd = friend;
			}
		}
		friends.add(profileToAdd);
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((friends == null) ? 0 : friends.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
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
		Profile other = (Profile) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (friends == null) {
			if (other.friends != null)
				return false;
		} else if (!friends.equals(other.friends))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		return true;
	}
	public Timestamp getLastConnection() {
		return lastConnection;
	}
	public void setLastConnection(Timestamp lastConnection) {
		this.lastConnection = lastConnection;
	}
	@Override
	public String toString() {
		return "Profile [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", login=" + login
				+ "]";
	}
	
}
