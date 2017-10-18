package tn.esprit.Apollo.Api;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.ArtWorkCategory;
import tn.esprit.Apollo.services.ArtWorkServiceRemote;
import tn.esprit.Authentificateur.JWTTokenNeeded;

@Path(value = "ArtWork")
@Stateless
@LocalBean
public class ArtWorkRest {
	@EJB
	private ArtWorkServiceRemote ARTWORK;
	/*
	 * @GET
	 * 
	 * @Path(value="test")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public ArtWork
	 * postJAXBElement(ArtWork e) { return (ArtWork)
	 * artworkserviceremote.read(primaryKey); }
	 */

	/*
	 * //display test
	 * 
	 * @GET
	 * 
	 * @Path(value="Display") public String display1(){ try { String
	 * a=artworkserviceremote.display(); return ""+a; } catch (Exception e) {
	 * System.out.println(e); } return "wrong in display";
	 * 
	 * }
	 */

	// recherche par id
	@GET
	@Path(value = "find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") int id) {
		if (ARTWORK.read(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(ARTWORK.read(id)).build();
	}
	@POST
	@Path(value = "add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("title") String title, @FormParam("descreption") String descreption,
			@FormParam("price") float price, @FormParam("category") ArtWorkCategory category) {
		ArtWork newArtWork = new ArtWork();
		newArtWork.setTitle(title);
		newArtWork.setDescreption(descreption);
		newArtWork.setCategory(category);
		newArtWork.setPrice(price);
		newArtWork.setUploadDate(new Date());
		newArtWork.setReleaseDate(new Date());
		System.out.println(newArtWork.toString());
		if (ARTWORK.create(newArtWork) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		// return Response.status(Response.Status.OK).build();
		return Response.ok().build();

	}

	@DELETE
	@Path(value = "remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") int id) {
		ArtWork newArtWork = new ArtWork();
		newArtWork.setId(id);
		System.out.println(newArtWork.toString());
		if (ARTWORK.delete(newArtWork) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		// return Response.status(Response.Status.OK).build();
		return Response.ok().build();

	}

	@POST
	@Path(value = "update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@FormParam("id") int id, @FormParam("title") String title,
			@FormParam("descreption") String descreption, @FormParam("price") float price,
			@FormParam("category") ArtWorkCategory category) {
		ArtWork newArtWork = new ArtWork();
		newArtWork.setId(id);
		newArtWork.setTitle(title);
		newArtWork.setDescreption(descreption);
		newArtWork.setCategory(category);
		newArtWork.setPrice(price);
		System.out.println(newArtWork.toString());
		if (ARTWORK.update(newArtWork) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		// return Response.status(Response.Status.OK).build();
		return Response.ok().build();

	}

	/*
	 * //add or modify
	 * 
	 * @GET
	 * 
	 * @Path("addOrModify/{id}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * addArtWork(@PathParam("id") int id){ ArtWork a =new ArtWork();
	 * a.setId(id); a.setPrice(50);
	 * if(artworkserviceremote.CreateOrUpdate(a)==false){ return
	 * Response.status(Response.Status.NOT_FOUND).build();} //return
	 * Response.status(Response.Status.OK).build(); return
	 * Response.ok().build(); }
	 * 
	 * //remove
	 * 
	 * @GET
	 * 
	 * @Path("remove/{id}")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * removeArtWork(@PathParam("id")int id){
	 * artworkserviceremote.remove(artworkserviceremote.find(id)); return
	 * Response.ok().build();
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * /*
	 * 
	 * @POST public void addArtWork(List<ArtWork> artworks){
	 * artworkserviceremote.addArtWork(artworks); }
	 * 
	 */

}
