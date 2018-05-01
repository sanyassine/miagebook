package resources;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import beans.Post;
import persistence.PostMapper;

@Path("status/")
public class StatusResource {
	
	// findByIdUser
	@GET
	@Path("user/{loginUser}")
	@Produces("application/json")
	public String findByUser(@PathParam("loginUser") String loginUser) {
		JsonObject json = JsonObject.EMPTY_JSON_OBJECT;
		List<Post> posts = PostMapper.getInstance().findPostsByLogin(loginUser);
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		for(Post post : posts) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

			objectBuilder.add("author_login", post.getAuthorLogin());
			objectBuilder.add("content", post.getContent());
			objectBuilder.add("datetime", post.getDate().getTime());
			objectBuilder.add("title", post.getTitle());
			objectBuilder.add("id_post", post.getIdPost());
			arrayBuilder.add(objectBuilder.build());
		}
		return arrayBuilder.build().toString();
	}
	
	@GET
	@Path("home/user/{loginUser}")
	@Produces("application/json")
	public String findHomeByUser(@PathParam("loginUser") String loginUser) {
		JsonObject json = JsonObject.EMPTY_JSON_OBJECT;
		List<Post> posts = PostMapper.getInstance().findPostHomeByLogin(loginUser, 10);
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		for(Post post : posts) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

			objectBuilder.add("author_login", post.getAuthorLogin());
			objectBuilder.add("content", post.getContent());
			objectBuilder.add("datetime", post.getDate().getTime());
			objectBuilder.add("title", post.getTitle());
			objectBuilder.add("id_post", post.getIdPost());
			arrayBuilder.add(objectBuilder.build());
		}
		return arrayBuilder.build().toString();
	}
}
