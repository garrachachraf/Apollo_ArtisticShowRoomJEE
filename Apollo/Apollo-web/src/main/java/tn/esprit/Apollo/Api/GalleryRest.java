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

import tn.esprit.Apollo.persistence.Gallery;
import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.Schedule;
import tn.esprit.Apollo.services.GalleryServiceRemote;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value="gallery")
@Stateless
@LocalBean


public class GalleryRest
{
	@EJB
	private GalleryServiceRemote galleryService ;

	@PUT
	@JWTTokenNeeded(role="GalleryOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGallery(Gallery gallery)
	{
	        galleryService.AddGallery(gallery);
			return Response.status(Status.OK).build();
	}
	
	@POST
	@Path(value="edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editGallery(Gallery gallery)
	{
		if (galleryService.EditGallery(gallery))
		return Response.status(Status.OK).build();
		else
      	return  Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path(value="erase/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteGallery(@PathParam("id") int id)
	{
		galleryService.DeleteGallery(id);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path(value="all/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll()
	{
		return Response.ok(galleryService.FindAllGalleries()).build();	 
	}
	
	@GET
	@Path(value="oneByName/{str}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findgalleryByName(@PathParam("str") String str)
	{
		Gallery tmp = galleryService.FindGalleryByName(str); 
		if (tmp != null)
			return Response.status(Status.OK).entity(tmp).build();
		else
			return  Response.status(Status.NO_CONTENT).build();
		
	}
	
	@GET
	@Path(value="allByZone/{str}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findGalleriesByLocation (@PathParam("str") String zone)
	{
		return Response.ok(galleryService.FindGalleriesByLocation(zone)).build();
	}
	
	@GET
	@Path(value="oneByOwner/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findgalleryByOwner(GalleryOwner owner)
	{
		return Response.ok(galleryService.FindGalleriesByOwner(owner)).build();
	}

	@GET
	@Path(value="schedule/between/{start}/and/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findGalleriesByDate(@PathParam("start") String start, @PathParam("end") String end)
	{
		return Response.ok(galleryService.FindGalleriesByDate(start,end)).build();
	}
	
	@POST
	@Path(value="affectToCalenddar/{id}/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response affectPlanToSchedule(@PathParam("id") int id ,Schedule sch)
	{
		galleryService.AddPlanToGallery(id, sch);
		return Response.status(Status.OK).build();	
	}	
	
	@DELETE
	@Path(value="calenddarOf/{idG}/erase/{idS}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFromCalendar(@PathParam("idG") int idGallery , @PathParam("idS") int idSch)
	{
		galleryService.CancelPlanToGallery( idSch ,idGallery) ;
		return Response.status(Status.OK).build();
	}
}
