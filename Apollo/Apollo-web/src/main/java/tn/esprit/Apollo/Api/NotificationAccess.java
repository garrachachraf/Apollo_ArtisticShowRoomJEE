package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.NotificationServiceLocal;

@Path(value="Notifications")
public class NotificationAccess {
	@EJB
	NotificationServiceLocal NotificationService ;
	@GET
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllByOne(@PathParam("id") String id){
		
		return Response.status(Status.OK).entity(NotificationService.GetAllNotifsByUserId(Integer.parseInt(id))).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){	
		return Response.status(Status.OK).entity(NotificationService.GetAllNotifs()).build();
	}

}
