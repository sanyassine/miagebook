package persistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Post;
import beans.Profile;
import beans.UserProfile;
import utils.sync.thread.PostSyncThread;
import utils.sync.thread.SyncObjectThread;

public class ProfileMapper extends DataMapper{
	private static ProfileMapper INSTANCE;
	public static UserProfile userSingleton;
	
	public static ProfileMapper getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ProfileMapper();
		}
		return INSTANCE;
	}
	private Map<String,Profile> map = new HashMap<String,Profile>();
	
	private ProfileMapper() {
		super();
		
	}
	CallableStatement findLoginStatement;
	public Profile find(String login) {
		if(map.containsKey(login)) {
			return map.get(login);
		}
		UserProfile user = null;
		try {
			if (findLoginStatement == null) {
				findLoginStatement = c.prepareCall("select login,password,firstname,lastname,email,isConnected,lastconnection from userprofiles"
						+" where login=?");
			}
			findLoginStatement.setString(1, login);
			ResultSet rs = findLoginStatement.executeQuery();
			if(rs.next()) {
				String firstname   = rs.getString("firstname");
				String lastname    = rs.getString("lastname");
				String email       = rs.getString("email");
				int isConnected = rs.getInt("isConnected");
				Timestamp lastConnection = rs.getTimestamp("lastconnection");
				user = new UserProfile();
				map.put(login, user);
				user.setEmail(email);user.setFirstName(firstname);
				user.setLastName(lastname);user.setLogin(login);
				user.setLastConnection(lastConnection);
				user.setLastConnection(lastConnection);
				if(isConnected == 1) {
					user.setConnected(true);
				}
				else {
					user.setConnected(false);
				}
				List<Profile> friends = findFriendsByProfile(user);
				user.setFriends(friends);
				
				user.setInserted(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SyncObjectThread thread = new PostSyncThread(user, this);
		return user;
	}
	
	CallableStatement findFriendsStatement;
	public List<Profile> findFriendsByProfile(Profile profile){
		List<Profile> friends = new ArrayList<Profile>();
		try {
			if(findFriendsStatement == null) {
				findFriendsStatement = c.prepareCall("select login_friend from friends where login_user=?");
			}
			findFriendsStatement.setString(1, profile.getLogin());
			ResultSet rs = findFriendsStatement.executeQuery();
			while(rs.next()) {
				String loginFriend = rs.getString("login_friend");
				Profile friend = find(loginFriend);
				if(friend != null) {
					friends.add(friend);
				}
			}
			profile.setFriends(friends);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friends;
	}
	
	CallableStatement findAllStatement;
	public List<Profile> findAll(){
		List<Profile> all = new ArrayList<>();
		try {
			if(findAllStatement == null) {
				findAllStatement = c.prepareCall("select login from userprofiles");
			}
			ResultSet rs = findAllStatement.executeQuery();
			while(rs.next()) {
				String login = rs.getString("login");
				Profile user = find(login);
				if(user != null) {
					all.add(user);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all ;
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
			map.put(profile.getLogin(), profile);
			profile.setInserted(true);
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	CallableStatement authentificateStatement;

	public UserProfile authenticate(String login, String password) {
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
				List<Profile> friends = findFriendsByProfile(user);
				user.setFriends(friends);
				userSingleton = user;
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
	public boolean updatePassword(Profile profile) {
		return false;
	}
	
	
	CallableStatement connectUserStatement;
	public void connectUser(UserProfile userProfile) {
		try {
			if(connectUserStatement == null) {
				connectUserStatement = c.prepareCall("update userprofiles set isConnected = 1, lastConnection = ? where login = ?");
			}
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			userProfile.setLastConnection(timestamp);
			userProfile.setConnected(true);
			connectUserStatement.setTimestamp(1, userProfile.getLastConnection());
			connectUserStatement.setString(2, userProfile.getLogin());
			connectUserStatement.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	CallableStatement discoUserStatement;
	public void disconnectUser(UserProfile userProfile) {
		try {
			if(discoUserStatement == null) {
				discoUserStatement = c.prepareCall("update userprofiles set isConnected = 0 , lastConnection = ? where login = ?");
			}
			userProfile.setConnected(false);
			userProfile.setLastConnection(new Timestamp(new Date().getTime()));
			discoUserStatement.setTimestamp(1, userProfile.getLastConnection());
			discoUserStatement.setString(2, userProfile.getLogin());
			discoUserStatement.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	CallableStatement addFriendStatement;
	public boolean addFriend(String login, String loginFriends) {
		try {
			if(addFriendStatement == null) {
				addFriendStatement = c.prepareCall("insert into friends values(?,?)");	
			}
			addFriendStatement.setString(1, login);
			addFriendStatement.setString(2, loginFriends);
			addFriendStatement.execute();
			addFriendStatement.setString(2, login);
			addFriendStatement.setString(1, loginFriends);
			addFriendStatement.execute();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	CallableStatement removeFriendStatement;
	public boolean removeFriend(Profile profile, String loginFriends) {
		try {
			if(removeFriendStatement == null) {
				removeFriendStatement = c.prepareCall("delete from friends where login_user=? and login_friend=?");	
			}
			removeFriendStatement.setString(1, profile.getLogin());
			removeFriendStatement.setString(2, loginFriends);
			removeFriendStatement.execute();
			removeFriendStatement.setString(2, profile.getLogin());
			removeFriendStatement.setString(1, loginFriends);
			removeFriendStatement.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateFriends(Profile profile) {
		return false;
	}
	public boolean updatePost(Profile profile, Post post) {
		return false;
	}
}
