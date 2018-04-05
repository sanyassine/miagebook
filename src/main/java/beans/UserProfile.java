package beans;

public class UserProfile extends Profile{
	private String password;
	
	public UserProfile() {
	
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
