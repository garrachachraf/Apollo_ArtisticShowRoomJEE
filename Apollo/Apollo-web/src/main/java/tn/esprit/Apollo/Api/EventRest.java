package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

import tn.esprit.Apollo.persistence.Event;
import tn.esprit.Apollo.persistence.Ticket;
import tn.esprit.Apollo.services.EventService;

@Path(value="event")
@Stateless
@LocalBean
public class EventRest {
	@EJB
	private EventService eventService;

	
	@GET
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("id") int id){
		if (eventService.FindEventExist(id))
		{
			Event event = eventService.findById(id);
			return Response.status(Status.OK).entity(event).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){
		for (Event e : eventService.readEvent()) {
			System.out.println(e.getId());
		}
		if (eventService.readEvent().isEmpty() == false)
			return Response.ok(eventService.readEvent()).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvent(Event event) {
		eventService.createEvent(event);
		return Response.status(Status.CREATED).build();
	}
	
	
	@DELETE
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvent(@PathParam("id") int id) {
		if (eventService.FindEventExist(id))
		{
			eventService.deleteEvent(id);
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTicket(Event event) {
		if(eventService.FindEventExist(event.getId()))
		{
			eventService.updateEvent(event);
			return Response.status(Status.ACCEPTED).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
}
//	@POST
//	@Path("create")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createEvent(Event e) {
//		if (EVENT.FindEventExist(e.getId()) == false)
//		{
//			EVENT.addEvent(e);
//			return Response.status(Response.Status.OK).build();
//		}
//		return Response.status(404).build();
//	}
//	
//	
//	//read(findbyid) to fix tickets//
//	@GET
//    @Path(value="find/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response read(@PathParam("id") String id){
//    	if	(EVENT.FindEventExist(Integer.valueOf(id)))
//    		return Response.ok(EVENT.FindById(Integer.valueOf(id))).build() ;
//    	
//    	return Response.status(Response.Status.NOT_FOUND).build();						
//    }
//	
//	@GET
//    @Path(value="find")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findAll(){
////		for (Event e : EVENT.findAll()) {
////			System.out.println(e);
////		}
//		return Response.ok(EVENT.findAll()).build();					
//    }
//	
//	//create
//    @GET
//    @Path(value="add/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response create(@PathParam("id") int id){
//    	Event newEvent = new Event();
//    	newEvent.setId(id);
//    	System.out.println(newEvent.toString());
//    	EVENT.addEvent(newEvent);
//    	if(EVENT.FindById(Integer.valueOf(id))==null){
//	        return Response.status(Response.Status.NOT_FOUND).build();}
//	    	//return Response.status(Response.Status.OK).build();
//	    	return Response.ok().build();
//    }
//    
//    @DELETE
//    @Path(value="remove/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response remove(@PathParam("id") int id){
//    	if (EVENT.FindEventExist(id)){
//    		EVENT.deleteEvent(id);
//    		return Response.ok().build();
//    	}
//    	return Response.status(Response.Status.NOT_FOUND).build();
//    	
//    	
////     Event newEvent = new Event();
////       newEvent.setId(id);
////    	if (EVENT.deleteEvent(id))
////    	{
////    		System.out.println(newEvent.toString());   	
////    		return Response.ok().build();
////    	}
////    	else
////    	{
////    		System.out.println("failed");
////    		return Response.status(Response.Status.NOT_FOUND).build();
////    	}
////	        return Response.status(Response.Status.NOT_FOUND).build();}
////	    	
//	    
//    }
//    
//    
//}
