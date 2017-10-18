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

import tn.esprit.Apollo.persistence.ShowRoom;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.UserServiceLocal;

@Path(value="Profile")
public class UserController {
	@EJB
	UserServiceLocal UserService ;
	
	@GET
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("id") int id){
		User user = UserService.FindUserById(id);
		return Response.status(Status.OK).entity(user).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProfile(User u) {
		UserService.CreateUser(u);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProfile(@PathParam("id") int id) {
		UserService.DeleteUser(id);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response AllUsers(){
		return Response.status(Status.OK).entity(UserService.GetAllUsers()).build();
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
	
}
