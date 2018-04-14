package services;

import beans.Profile;
import persistence.ProfileMapper;

public class FriendsService {
	private static ProfileMapper profileMapper = ProfileMapper.getInstance();
	
	public static void makeFriends(Profile user1, String login2) {
		String login1 = user1.getLogin();
		Profile friend = profileMapper.find(login2);
		
		friend.getFriends().add(friend);
		user1.getFriends().add(friend);
		
		profileMapper.addFriend(login1, login2);
	}
	
	public static void deleteFriends(Profile user1, String login2) {
		Profile friend = profileMapper.find(login2);
		String login1 = user1.getLogin();
		
		user1.removeFriendByLogin(login2);
		friend.removeFriendByLogin(login1);
		
		profileMapper.removeFriend(user1, login2);
	}
}
