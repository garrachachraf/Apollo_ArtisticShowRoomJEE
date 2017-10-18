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
import tn.esprit.Apollo.services.TicketService;

@Path(value="ticket")
@Stateless
@LocalBean
public class TicketsController {
	
	@EJB
	private TicketService ticketService;
	
	@GET
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("id") int id){
		if (ticketService.FindTicketExist(id))
		{
			Ticket ticket = ticketService.findById(id);
			return Response.status(Status.OK).entity(ticket).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){
		for (Ticket t : ticketService.readTicket()) {
			System.out.println(t.getId());
		}
		if (ticketService.readTicket().isEmpty() == false)
			return Response.ok(ticketService.readTicket()).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTicket(Ticket ticket) {
		ticketService.createTicket(ticket);
		return Response.status(Status.CREATED).build();
	}
	
	
	@DELETE
	@Path(value="{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvent(@PathParam("id") int id) {
		if (ticketService.FindTicketExist(id))
		{
			ticketService.deleteTicket(id);
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
	@Path(value="byevent/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllTickets(@PathParam("id") int id){
		if (ticketService.readTicketsOfEvent(id).isEmpty() == false)
			return Response.ok(ticketService.readTicketsOfEvent(id)).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTicket(Ticket ticket) {
		if(ticketService.FindTicketExist(ticket.getId()))
		{
			ticketService.updateTicket(ticket);
			return Response.status(Status.ACCEPTED).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}
