package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.RatingService;

@Path(value="rating")
public class RatingController {
	@EJB
	RatingService ratingService;
	
	@GET
	@Path(value="{artWorkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAverageRating(@PathParam("artWorkId") int id){
		return Response.status(Status.OK).entity(ratingService.getAverageRating(id)).build();
	}
	
	@GET
	@Path(value="myrating/{artWorkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyRating(@PathParam("artWorkId") int artWorkId){
		User user = null;
		return Response.status(Status.OK).entity(ratingService.findByArtworkAndUser(artWorkId, user.getId())).build();
	}
	
	@POST
	@Path(value="{artWorkId}/{value}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRating(@PathParam("value") float value,@PathParam("artWorkId") int artWorkId) {
		User user=null; // getcurrentuser
		ratingService.addRating(artWorkId, value, user);
		return Response.status(Status.CREATED).build();
	}
}
