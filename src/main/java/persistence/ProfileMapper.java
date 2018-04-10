package persistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import beans.Post;
import beans.Profile;
import beans.UserProfile;
import utils.sync.thread.PostSyncThread;
import utils.sync.thread.SyncObjectThread;

public class ProfileMapper extends DataMapper{
	private Map<String,Profile> map = new HashMap<String,Profile>();
	
	public ProfileMapper() {
		super();
	}
	public Profile find(String login) {
		if(map.containsKey(login)) {
			return map.get(login);
		}
		Profile profile = new Profile();
		//provide from database
		
		SyncObjectThread thread = new PostSyncThread(profile, this);
		return profile;
	}
	
	CallableStatement insertProfileStatement;

	public boolean insert(UserProfile profile) {
		boolean res = false;
		try {
			if (insertProfileStatement == null) {
				insertProfileStatement = c.prepareCall(
						"insert into userprofiles(login,password,firstname,lastname,email) values (?,?,?,?,?)");
			}
			insertProfileStatement.setString(1, profile.getLogin());
			insertProfileStatement.setString(2, profile.getPassword());
			insertProfileStatement.setString(3, profile.getFirstName());
			insertProfileStatement.setString(4, profile.getLastName());
			insertProfileStatement.setString(5, profile.getEmail());
			
			insertProfileStatement.execute();	
			c.commit();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	CallableStatement authentificateStatement;

	public UserProfile authentificate(String login, String password) {
		UserProfile user = null;
		try {
			if (authentificateStatement == null) {
				authentificateStatement = c.prepareCall("select login,password,firstname,lastname,email from userprofiles"
						+" where login=? and password=?");
			}
			authentificateStatement.setString(1, login);
			authentificateStatement.setString(2, password);
			ResultSet rs = authentificateStatement.executeQuery();
			if(rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname  = rs.getString("lastname");
				String email     = rs.getString("email");
				user = new UserProfile();
				user.setEmail(email);user.setFirstName(firstname);
				user.setLastName(lastname);user.setLogin(login);
				user.setPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public boolean updateFirstname(Profile profile) {
		return false;
	}
	public boolean updateLastname(Profile profile) {
		return false;
	}
	public boolean updateEmail(Profile profile) {
		return false;
	}
	public boolean addFriends(Profile profile, int idFriends) {
		return false;
	}
	public boolean addPost(Profile profile, Post post) {
		return false;
	}
	public boolean updateFriends(Profile profile) {
		return false;
	}
	public boolean updatePost(Profile profile) {
		return false;
	}
}
