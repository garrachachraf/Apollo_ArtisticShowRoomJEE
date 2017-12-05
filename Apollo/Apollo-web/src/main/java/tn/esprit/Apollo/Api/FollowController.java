package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.jsonwebtoken.Jwts;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.FollowService;
import tn.esprit.Apollo.services.UserService;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="follow")
public class FollowController {
	@EJB
	 FollowService followService;
	@EJB
	UserService userService;

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
	@JWTTokenNeeded(role="user")
	@Path(value="{artistId}/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollow(@PathParam("artistId") int artistId,@PathParam("userId") int userId) {
		if(followService.findFollow(userId, artistId) != null)
			return Response.status(Status.OK).entity(followService.findFollow(userId, artistId)).build();
		else
			return Response.status(Status.NO_CONTENT).entity(followService.findFollow(userId, artistId)).build();
	}
	
	@POST
	@JWTTokenNeeded(role="user")
	@Path(value="{artistId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response follow(@PathParam("artistId") int artistId,@Context HttpHeaders header) {
		User user = usernameToken(header);
		followService.follow(artistId, user.getId());
		return Response.status(Status.CREATED).build();
	}
	@DELETE
	@JWTTokenNeeded(role="user")
	@Path(value="{artistId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unfollow(@PathParam("artistId") int artistId,@Context HttpHeaders header) {
		User user = usernameToken(header);
		followService.unfollow(artistId, user.getId());
		return Response.status(Status.CREATED).build();
	}
	
	public  User usernameToken(HttpHeaders header){
		String authorizationHeader = header.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
		String s = "maissen";
		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length() + 1).trim();
		String userName=Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getSubject();
		System.out.println(userName);
		
		return userService.FindUserByUsername(userName);
	}
}
