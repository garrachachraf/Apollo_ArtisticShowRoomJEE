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
import tn.esprit.Apollo.services.ArtWorkService;
@Path(value="ArtWork")
@Stateless
@LocalBean
public class ArtWorkRest{
	@EJB
	private ArtWorkService ARTWORK; 
	  /*
	@GET
	@Path(value="test")
	@Produces(MediaType.APPLICATION_JSON)
	public ArtWork postJAXBElement(ArtWork e) {
		return (ArtWork) artworkserviceremote.read(primaryKey);
	}
	*/
	
	
	
	
	/*
	//display test
	    @GET
	    @Path(value="Display")
	     public String display1(){
	    	try {
	    		String a=artworkserviceremote.display();
	    		  return ""+a;
			} catch (Exception e) {
				System.out.println(e);
			}
	     return "wrong in display";
		
	    } */
	  
	    //recherche par id
	    @GET
	    @Path(value="find/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response find(@PathParam("id") String id){
	    	if	(ARTWORK.read(Integer.valueOf(id))==null){
	    		return Response.status(Response.Status.NOT_FOUND).build();}
				return Response.ok(ARTWORK.read(Integer.valueOf(id))).build() ;				
	    }
	    @GET
	    @Path(value="add/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response create(@PathParam("id") int id){
	     ArtWork newArtWork = new ArtWork();
	     
	       
	    	newArtWork.setPrice(id);
	    	System.out.println(newArtWork.toString());
	    	if(ARTWORK.create(newArtWork)==null){
		        return Response.status(Response.Status.NOT_FOUND).build();}
		    	//return Response.status(Response.Status.OK).build();
		    	return Response.ok().build();
	    	
	    }
	    @GET
	    @Path(value="remove/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response remove(@PathParam("id") int id){
	     ArtWork newArtWork = new ArtWork();
	       newArtWork.setId(id);
	    	System.out.println(newArtWork.toString());
	    	if(ARTWORK.delete(newArtWork)==false){
		        return Response.status(Response.Status.NOT_FOUND).build();}
		    	//return Response.status(Response.Status.OK).build();
		    	return Response.ok().build();
	    	
	    }
	    @GET
	    @Path(value="update/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response update(@PathParam("id") int id){
	     ArtWork newArtWork = new ArtWork();
	       newArtWork.setId(id);
	    	System.out.println(newArtWork.toString());
	    	if(ARTWORK.update(newArtWork)==false){
		        return Response.status(Response.Status.NOT_FOUND).build();}
		    	//return Response.status(Response.Status.OK).build();
		    	return Response.ok().build();
	    	
	    }
	    
	    
	    
	    /*
	    //add or modify
	    @GET
	    @Path("addOrModify/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response addArtWork(@PathParam("id") int id){
	    	ArtWork a =new ArtWork();
	    	a.setId(id);
	    	a.setPrice(50);
	    	if(artworkserviceremote.CreateOrUpdate(a)==false){
	        return Response.status(Response.Status.NOT_FOUND).build();}
	    	//return Response.status(Response.Status.OK).build();
	    	return Response.ok().build();
	    }
	    
	    //remove
	    @GET
	    @Path("remove/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response removeArtWork(@PathParam("id")int id){
	    	artworkserviceremote.remove(artworkserviceremote.find(id));
	    	return Response.ok().build();
	    	
	    	
	    }
	    
	    
	    
	  /*  
	   @POST
	   public void addArtWork(List<ArtWork> artworks){
		   artworkserviceremote.addArtWork(artworks);
		   }
	    
	    */
	    
	    
	    
	
	  
	

}
