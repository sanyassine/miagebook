package persistence;

import java.util.HashMap;
import java.util.Map;

import beans.Post;
import beans.Profile;
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
	public boolean insert(Profile profile) {
		return false;
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
