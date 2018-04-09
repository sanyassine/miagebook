package persistence.connection.utils;


public class AuthentificationData {

	private String login;
	private String password;
	public AuthentificationData(String login, String password) {
		this.login = login;
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}

	
}
