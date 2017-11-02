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
import tn.esprit.Apollo.services.UserService;
import tn.esprit.Apollo.services.WishListService;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="wishlist")
public class WishListController {
	@EJB
	WishListService wishListService;
	@EJB
	UserService userService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role="user")
	public Response getWishList(@Context HttpHeaders header){
		User user = usernameToken(header);
		return Response.status(Status.OK).entity(wishListService.getWishList(user)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role="user") // role Admin
	@Path(value="all")
	public Response getAllWishLists(){
		return Response.status(Status.OK).entity(wishListService.getAllWishLists()).build();
	}
	
	@GET
	@Path(value="total")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role="user")
	public Response getTotal(@Context HttpHeaders header){
		User user = usernameToken(header);
		return Response.status(Status.OK).entity(wishListService.getTotal(user)).build();
	}	
	@POST
	@Path(value="{id}")
	@JWTTokenNeeded(role="user")
	public Response addItem(@PathParam("id")int itemId,@Context HttpHeaders header) {
		User user = usernameToken(header);
		wishListService.addItem(itemId,user);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path(value="{id}")
	@JWTTokenNeeded(role="user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("id") int id,@Context HttpHeaders header) {
		User user = usernameToken(header);
		wishListService.deleteItem(id,user);
		return Response.status(Status.OK).build();
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
