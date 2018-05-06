package resources;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import beans.Profile;
import persistence.ProfileMapper;
import services.FriendsService;

@Path("friends/")
public class FriendsResource {

	@GET
	@Path("{user}/{loginFriend}")
	@Produces("application/json")
	public String addRemoveFriend(@PathParam("user") String user,@PathParam("loginFriend") String friend) {
		JsonObject json = JsonObject.EMPTY_JSON_OBJECT;
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		Profile userProfile = ProfileMapper.getInstance().find(user);
		Profile friendProfile = ProfileMapper.getInstance().find(friend);
		if(userProfile.getFriends().contains(friendProfile)) {
			FriendsService.deleteFriends(userProfile, friend);
			objectBuilder.add("add_friend",false);
		}
		else {
			FriendsService.makeFriends(userProfile, friend);
			objectBuilder.add("add_friend",true);
		}
		objectBuilder.add("loginFriend",friend);
		arrayBuilder.add(objectBuilder);
		return arrayBuilder.build().toString();
	}	
}