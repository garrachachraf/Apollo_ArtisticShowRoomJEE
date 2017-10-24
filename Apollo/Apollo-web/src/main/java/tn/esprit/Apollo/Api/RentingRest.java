package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Apollo.persistence.Renting;
import tn.esprit.Apollo.services.RentingServiceRemote;

@Path(value="renting")
@Stateless
@LocalBean
public class RentingRest 
{
	@EJB
	private RentingServiceRemote rentingService ;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRenting(Renting renting)
	{
		rentingService.CreateRenting(renting);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path(value="erase/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRenting(@PathParam("id") int id)
	{
		rentingService.DeleteRenting(id);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path(value="all/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllRentings()
	{
		return Response.ok(rentingService.findAllRentings()).build();	
	}
	
	@GET
	@Path(value="artist/{id}/all/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findRentingsOfArtist(@PathParam("id") int id)
	{
		return Response.ok(rentingService.findRentingByArtist(id)).build();	
	}
	
	@GET
	@Path(value="gallery/{id}/all/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findRentingsOfGallery(@PathParam("id") int id)
	{
		return Response.ok(rentingService.findRentingByGallery(id)).build();	
	}	
	


}
