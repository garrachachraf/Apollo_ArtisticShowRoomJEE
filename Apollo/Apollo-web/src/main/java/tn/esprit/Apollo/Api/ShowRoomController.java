package tn.esprit.Apollo.Api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.jsonwebtoken.Jwts;
import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.ShowRoom;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.ShowRoomService;
import tn.esprit.Apollo.services.UserService;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="showroom")
public class ShowRoomController {
	@EJB
	ShowRoomService showroomService;
	@EJB
	UserService userService;
	
	@GET
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("id") int id){
		ShowRoom showroom = showroomService.findOne(id);
		if(showroom != null) {
			return Response.status(Status.OK).entity(showroom).build();
		}
		else {
			return Response.status(Status.NO_CONTENT).build();
		}
	}
	
	@GET
	//@JWTTokenNeeded(role="user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){
		return Response.status(Status.OK).entity(showroomService.findAll()).build();
	}
	@GET
	@Path(value="artist/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByArtist(@PathParam("id") int id){
		return Response.status(Status.OK).entity(showroomService.findByArtist(id)).build();
	}
	@GET
	@Path(value="keyword")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByKeyWord(@QueryParam("keyword") String keyword){
		return Response.status(Status.OK).entity(showroomService.findByKeyWord(keyword)).build();
	}
	@POST
	//@JWTTokenNeeded(role="user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createShowroom(ShowRoom showroom,@Context HttpHeaders header) {
		showroomService.createShowRoom(showroom);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@JWTTokenNeeded(role="user")
	@Path(value="artworks/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArtworksToShowroom(List<ArtWork> artworks,@Context HttpHeaders header,@PathParam("id") int showroomId) {
		User user = usernameToken(header);
		showroomService.addArtworks(artworks,user,showroomId);
		return Response.status(Status.CREATED).build();
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateShowroom(ShowRoom showroom) {
		showroomService.updateShowRoom(showroom);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path(value="{id}")
	//@JWTTokenNeeded(role="user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteShowroom(@PathParam("id") int id) {
		showroomService.deleteShowroom(id);
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
