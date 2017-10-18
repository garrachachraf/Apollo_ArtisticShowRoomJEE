package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.Collection;

import tn.esprit.Apollo.services.CollectionService;
import tn.esprit.Apollo.services.CollectionServiceRemote;
@Path(value="Collection")
@Stateless
@LocalBean
public class CollectionRest {
	@EJB
	private CollectionService Collection; 
	//recherche par id
	 @GET
	    @Path(value="find/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response find(@PathParam("id") String id) throws Exception{
	    	if	(Collection.read(Integer.valueOf(id))==null){
	    		return Response.status(Response.Status.NOT_FOUND).build();}
				return Response.ok(Collection.read(Integer.valueOf(id))).build() ;				
	    }
	    @GET
	    @Path(value="add/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response create(@PathParam("id") int id){
	     Collection newArtWork = new Collection();
	     
	       
	    	
	    	System.out.println(newArtWork.toString());
	    	if(Collection.create(newArtWork)==null){
		        return Response.status(Response.Status.NOT_FOUND).build();}
		    	//return Response.status(Response.Status.OK).build();
		    	return Response.ok().build();
	    	
	    }
	    @GET
	    @Path(value="remove/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response remove(@PathParam("id") int id){
	     Collection newArtWork = new Collection();
	       newArtWork.setId(id);
	    	System.out.println(newArtWork.toString());
	    	if(Collection.delete(newArtWork)==false){
		        return Response.status(Response.Status.NOT_FOUND).build();}
		    	//return Response.status(Response.Status.OK).build();
		    	return Response.ok().build();
	    	
	    }
	    @GET
	    @Path(value="update/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response update(@PathParam("id") int id){
	     Collection newArtWork = new Collection();
	       newArtWork.setId(id);
	    	System.out.println(newArtWork.toString());
	    	if(Collection.update(newArtWork)==false){
		        return Response.status(Response.Status.NOT_FOUND).build();}
		    	//return Response.status(Response.Status.OK).build();
		    	return Response.ok().build();
	    	
	    }
	    
	    
}
