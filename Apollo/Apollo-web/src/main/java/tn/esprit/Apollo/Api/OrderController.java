package tn.esprit.Apollo.Api;

import java.util.Iterator;
import java.util.List;

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
import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.ArtWorkService;
import tn.esprit.Apollo.services.OrderService;
import tn.esprit.Apollo.services.UserService;
import tn.esprit.Apollo.services.WishListService;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="order")
public class OrderController {
	@EJB
	OrderService orderService;
	@EJB
	WishListService wishListService;
	@EJB
	UserService userService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded // role Admin
	public Response getAllorders(){
		return Response.status(Status.OK).entity(orderService.GetAllOrders()).build();
	}
	
	@GET
	@Path(value="{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public Response getByUser(@PathParam("userId")int userId){
		return Response.status(Status.OK).entity(orderService.GetOrdersByUser(userId)).build();
	}	
	@POST
	@JWTTokenNeeded
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(List<ArtWork> artworks,@Context HttpHeaders header) {
		System.out.println("hererere255");
		for (ArtWork artWork : artworks) {
		}
		User user = usernameToken(header);
		orderService.createOrder(artworks,user);
		for (ArtWork artWork : artworks) {
			wishListService.deleteItem(artWork.getId(), user);
		}
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
