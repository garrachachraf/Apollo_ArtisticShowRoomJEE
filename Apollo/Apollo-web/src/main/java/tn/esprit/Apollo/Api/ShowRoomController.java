package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Apollo.persistence.ShowRoom;
import tn.esprit.Apollo.services.ShowRoomServiceLocal;

@Path(value="showroom")
public class ShowRoomController {
	@EJB
	ShowRoomServiceLocal showroomService;
	
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){
		return Response.status(Status.OK).entity(showroomService.findAll()).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createShowroom(ShowRoom showroom) {
		showroomService.createShowRoom(showroom);
		return Response.status(Status.CREATED).build();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateShowroom(ShowRoom showroom) {
		showroomService.updateShowRoom(showroom);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteShowroom(@PathParam("id") int id) {
		showroomService.deleteShowroom(id);
		return Response.status(Status.OK).build();
	}
	
}
