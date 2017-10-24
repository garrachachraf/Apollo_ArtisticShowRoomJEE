package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.FollowService;

@Path(value="follow")
public class FollowController {
	@EJB
	 FollowService followService;
	
	@GET
	@Path(value="artist/{artistId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollowers(@PathParam("artistId") int artistId){
		return Response.status(Status.OK).entity(followService.getFollowers(artistId)).build();
	}
	
	@GET
	@Path(value="user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollowings(@PathParam("userId") int userId){
		return Response.status(Status.OK).entity(followService.getFollowings(userId)).build();
	}
	
	@GET
	@Path(value="user/count/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response countFollowings(@PathParam("userId") int userId){
		return Response.status(Status.OK).entity(followService.countFollowings(userId)).build();
	}
	
	@GET
	@Path(value="artist/count/{artistId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response countFollowers(@PathParam("artistId") int artistId){
		return Response.status(Status.OK).entity(followService.countFollowers(artistId)).build();
	}
	@GET
	@Path(value="{artistId}/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollow(@PathParam("artistId") int artistId,@PathParam("userId") int userId) {
		return Response.status(Status.OK).entity(followService.findFollow(userId, artistId)).build();
	}
	
	
	@POST
	@Path(value="{artistId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response follow(@PathParam("artistId") int artistId) {
		User user = new User(); // getcurrentuser
		followService.follow(artistId, user.getId());
		return Response.status(Status.CREATED).build();
	}
	@DELETE
	@Path(value="{artistId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unfollow(@PathParam("artistId") int artistId) {
		User user=null; // getcurrentuser
		followService.unfollow(artistId, user.getId());
		return Response.status(Status.CREATED).build();
	}
}
