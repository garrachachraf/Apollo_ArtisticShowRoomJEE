package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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
import tn.esprit.Apollo.services.RatingService;
import tn.esprit.Apollo.services.UserService;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="rating")
public class RatingController {
	@EJB
	RatingService ratingService;
	@EJB
	UserService userService;
	
	@GET
	@Path(value="{artWorkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAverageRating(@PathParam("artWorkId") int id){
		return Response.status(Status.OK).entity(ratingService.getAverageRating(id)).build();
	}
	
	@GET
	@JWTTokenNeeded(role="user")
	@Path(value="myrating/{artWorkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyRating(@PathParam("artWorkId") int artWorkId,@Context HttpHeaders header){
		Double rating =(double) 0;
		User user =usernameToken(header);
		if(ratingService.findByArtworkAndUser(artWorkId, user.getId()) != null)
			rating = (double) ratingService.findByArtworkAndUser(artWorkId, user.getId()).getRatingValue();
		return Response.status(Status.OK).entity(rating).build();
	}
	
	@POST
	@JWTTokenNeeded(role="user")
	@Path(value="{artWorkId}/{value}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRating(@PathParam("value") float value,@PathParam("artWorkId") int artWorkId,@Context HttpHeaders header) {
		User user =usernameToken(header);
		ratingService.addRating(artWorkId, value, user);
		return Response.status(Status.CREATED).entity(ratingService.getAverageRating(artWorkId)).build();
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
