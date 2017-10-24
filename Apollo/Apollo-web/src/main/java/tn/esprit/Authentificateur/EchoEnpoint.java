package tn.esprit.Authentificateur;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;





@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
public class EchoEnpoint {

	 @GET
	    public Response echo(@QueryParam("message") String message) {
	        return Response.ok().entity(message == null ? "no message" : message).build();
	    }
	 //test the filter by role artist
	    @GET
	    @Path("jwt")
	    @JWTTokenNeeded(role="Artist")
	    public Response echoWithJWTToken(@QueryParam("message") String message) {
	        return Response.ok().entity(message == null ? "jwt rick i'm jwt rick" : message).build();
	    }
	
}
