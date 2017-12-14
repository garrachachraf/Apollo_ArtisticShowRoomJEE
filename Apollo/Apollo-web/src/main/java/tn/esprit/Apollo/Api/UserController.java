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
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.jsonwebtoken.Jwts;

import javax.ws.rs.core.Context;

import tn.esprit.Apollo.persistence.Admin;
import tn.esprit.Apollo.persistence.Artist;
import tn.esprit.Apollo.persistence.Event;
import tn.esprit.Apollo.persistence.Gallery;
import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.ShowRoom;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.AdminServiceLocal;
import tn.esprit.Apollo.services.ArtistServiceLocal;
import tn.esprit.Apollo.services.GalleryOwnerServiceLocal;
import tn.esprit.Apollo.services.UserServiceLocal;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="Profile")
public class UserController {
	@EJB
	protected
	UserServiceLocal UserService ;
	@EJB
	ArtistServiceLocal ArtistService ;
	@EJB
	GalleryOwnerServiceLocal GalleryOwnerService ;
	@EJB
	AdminServiceLocal AdminService ;
	

	

	@GET
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("id") int id){
		User user = UserService.FindUserById(id);
		user.setPassword("Not Allowed to see ");
		
		return Response.status(Status.OK).entity(user).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProfile(User u) {

		if (u.getRole().equals("Artist")) {
			System.out.println("here !");
			Artist a = new Artist() ;
			a.setCity(u.getCity());
			a.setCountry(u.getCountry());
			a.setEmail(u.getEmail());
			a.setFirstname(u.getFirstname());
			a.setGender(u.getGender());
			a.setLastname(u.getLastname());
			a.setPassword(u.getPassword());
			a.setState(u.getState());
			a.setStreet(u.getStreet());
			a.setUserName(u.getUserName());
			a.setZipCode(u.getZipCode());
			a.setImagePath(u.getImagePath());
			ArtistService.CreateUser(a);
		}
		else if (u.getRole().equals("GalleryOwner")) {
			GalleryOwner g = new GalleryOwner() ;
			g.setCity(u.getCity());
			g.setCountry(u.getCountry());
			g.setEmail(u.getEmail());
			g.setFirstname(u.getFirstname());
			g.setGender(u.getGender());
			g.setLastname(u.getLastname());
			g.setPassword(u.getPassword());
			g.setState(u.getState());
			g.setStreet(u.getStreet());
			g.setUserName(u.getUserName());
			g.setZipCode(u.getZipCode());
			g.setImagePath(u.getImagePath());
			GalleryOwnerService.CreateUser(g);
		}
		else if (u.getRole().equals("Admin")) {
			Admin au = new Admin() ;
			au.setCity(u.getCity());
			au.setCountry(u.getCountry());
			au.setEmail(u.getEmail());
			au.setFirstname(u.getFirstname());
			au.setGender(u.getGender());
			au.setLastname(u.getLastname());
			au.setPassword(u.getPassword());
			au.setState(u.getState());
			au.setStreet(u.getStreet());
			au.setUserName(u.getUserName());
			au.setZipCode(u.getZipCode());
			au.setImagePath(u.getImagePath());
			AdminService.CreateAdmin(au);
		}
		else {
			UserService.CreateUser(u);
		}
		

		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@JWTTokenNeeded(role="user")
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProfile(@PathParam("id") int id) {
		UserService.DeleteUser(id);
		return Response.status(Status.OK).build();
	}
	
	@GET
	//@JWTTokenNeeded()
	@Produces(MediaType.APPLICATION_JSON)
	public Response AllUsers(){
		
		List<User> lst = UserService.GetAllUsers() ;
		lst.forEach(u->u.setPassword("not allowed to see"));
		return Response.status(Status.OK).entity(lst).build();
	}
	
	@GET
	//@JWTTokenNeeded()
	@Path("/artists")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AllArtists(@Context HttpHeaders header){
		
		List<Artist> lst = ArtistService.GetAllUsers() ;
		lst.forEach(u->u.setPassword("not allowed to see"));
		return Response.status(Status.OK).entity(lst).build();
	}
	
	@GET
	//@JWTTokenNeeded()
	@Path("/gowners")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AllGalleryOwners(@Context HttpHeaders header){
		
		List<GalleryOwner> lst = GalleryOwnerService.GetAllUsers() ;
		lst.forEach(u->u.setPassword("not allowed to see"));
		return Response.status(Status.OK).entity(lst).build();
	}

	@POST
	@Path(value="Check")
	public Response checklogins( User u ) {
		String password = u.getPassword();
		String  username = u.getUserName();
		if(UserService.loginCheck(username, password))
		{
		return Response.status(Status.OK).entity("{success: true }").build();
		}
		return Response.status(Status.OK).entity("{Error: Wrong }").build();
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTicket(Artist user) {
		if(ArtistService.FindUserById(user.getId()) !=null)
		{	
			
			ArtistService.UpdateUser(user);
			return Response.status(Status.ACCEPTED).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	public User usernameToken(String authorizationHeader){
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
		String s = "maissen";
		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length() + 1).trim();
		String id=Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getId();
		return UserService.FindUserById(Integer.valueOf(id));
	}
	
}
