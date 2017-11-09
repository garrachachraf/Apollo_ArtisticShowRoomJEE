package tn.esprit.Apollo.Api;


import java.io.IOException;

import java.util.Date;

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
import com.google.zxing.WriterException;
import tn.esprit.Apollo.persistence.Ticket;
import tn.esprit.Apollo.services.TicketService;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="tickets")
@Stateless
@LocalBean
public class TicketsController {
	
	@EJB
	private TicketService ticketService;

	
	@GET
// 	@JWTTokenNeeded(role="Admin")
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
// 	@JWTTokenNeeded(role="Admin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){
		if (ticketService.readTicket().isEmpty() == false)
			return Response.ok(ticketService.readTicket()).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
// 	@JWTTokenNeeded(role="user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTicket(Ticket ticket) throws IOException, WriterException, InterruptedException {
//        String latitude = ticket.getEvent().getGallery().getLocation().getLatitude().toString();
//        String longitude = ticket.getEvent().getGallery().getLocation().getLongitude().toString();
//		System.out.println(ticket.getEvent().getGallery().getId().toString()+ ticket.getTitle() + ticket.getEvent().getId().toString());
//		System.out.println(longitude+latitude);
		if (ticketService.readTicketsOfEvent(ticket.getEvent().getId()).size() < ticket.getEvent().getCapacity())
		{
			ticket.setOrderDate(new Date());
			ticketService.createTicket(ticket);
			//generateQr(ticket);
			QrcodeAndMapGenerator.generateQr(ticket);
			TicketPdf.generatePdf(ticket);
			return Response.status(Status.CREATED).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	
	@DELETE
	@Path(value="{id}")
// 	@JWTTokenNeeded(role="Admin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvent(@PathParam("id") int id) {
		if (ticketService.FindTicketExist(id))
		{
			ticketService.deleteTicket(id);
			return Response.status(204).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
// 	@JWTTokenNeeded(role="Admin")
	@Path(value="events/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllTickets(@PathParam("id") int id){
		if (ticketService.readTicketsOfEvent(id).isEmpty() == false)
			return Response.ok(ticketService.readTicketsOfEvent(id)).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
// 	@JWTTokenNeeded(role="Admin")
	@Path(value="{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTicket(@PathParam("id") int id,Ticket ticket) {
		if(ticketService.FindTicketExist(id))
		{
			ticket.setId(id);
			ticketService.updateTicket(ticket);
			return Response.status(Status.ACCEPTED).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
 //	@JWTTokenNeeded(role="Admin")
	@Path(value="events/sum/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findNumberTickets(@PathParam("id") int id){
		if (ticketService.readTicketsOfEvent(id).isEmpty() == false)
			return Response.ok(ticketService.readTicketsOfEvent(id).size()).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
// 	@JWTTokenNeeded(role="Admin")
	@Path(value="events/vip/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllTicketsVip(@PathParam("id") int id){
		if (ticketService.readTicketsOfEvent(id).isEmpty() == false)
			return Response.ok(ticketService.readTicketsOfEventVip(id)).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
 //	@JWTTokenNeeded(role="Admin")
	@Path(value="events/normal/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllTicketsNormal(@PathParam("id") int id){
		if (ticketService.readTicketsOfEvent(id).isEmpty() == false)
			return Response.ok(ticketService.readTicketsOfEventNormal(id)).build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	

  
}
