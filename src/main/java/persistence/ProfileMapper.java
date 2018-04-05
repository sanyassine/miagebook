package persistence;

import java.util.HashMap;
import java.util.Map;

import beans.Post;
import beans.Profile;

public class ProfileMapper {
	private Map<String,Profile> map = new HashMap<String,Profile>();
	public Profile find(String login) {
		return null;
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
