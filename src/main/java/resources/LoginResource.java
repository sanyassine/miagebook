package resources;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import services.RegisterService;

@Path("login/")
public class LoginResource {

	@GET
	@Path("{loginUser}")
	@Produces("application/json")	
	public String checkLogin(@PathParam("loginUser") String login) {
		JsonObject json = JsonObject.EMPTY_JSON_OBJECT;
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		if(RegisterService.loginAlreadyExists(login)) {
			objectBuilder.add("exists",true);
		}
		else {
			objectBuilder.add("exists",false);
		}
		arrayBuilder.add(objectBuilder);
		return arrayBuilder.build().toString();
	}
}
