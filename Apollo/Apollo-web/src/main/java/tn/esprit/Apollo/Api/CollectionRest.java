package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Jwts;
import tn.esprit.Apollo.persistence.Artist;
import tn.esprit.Apollo.persistence.Collection;
import tn.esprit.Apollo.services.CollectionServiceRemote;
import tn.esprit.Apollo.services.UserServiceLocal;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value = "Collection")
@Stateless

public class CollectionRest {
	@EJB
	private CollectionServiceRemote Collection;
	@EJB
	UserServiceLocal UserService ;

	// recherche par id
	@GET
	@Path(value = "find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") String id) {
		if (Collection.read(Integer.valueOf(id)) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(Collection.read(Integer.valueOf(id))).build();
	}

	@POST
	@JWTTokenNeeded(role="Artist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Collection col,@HeaderParam("AUTHORIZATION") String token) {
		if (Collection.create(col) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		// return Response.status(Response.Status.OK).build();
		return Response.ok().build();

	}

	@DELETE
	@JWTTokenNeeded(role={"Artist"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(Collection col) {
		
		
		if (Collection.delete(col) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		// return Response.status(Response.Status.OK).build();
		return Response.ok().build();

	}

	@PUT
	@JWTTokenNeeded(role={"Artist"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Collection col) {
		if (Collection.update(col) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		// return Response.status(Response.Status.OK).build();
		return Response.ok().build();

	}

}
