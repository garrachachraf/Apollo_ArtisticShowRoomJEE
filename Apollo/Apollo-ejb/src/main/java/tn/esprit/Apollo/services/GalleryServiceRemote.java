package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Gallery;
import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.Schedule;

@Remote
public interface GalleryServiceRemote {
	

	
	public void CreateGalleryOwner (GalleryOwner gOwner);
	public void     AddGallery(Gallery gallery);
	public boolean  EditGallery(Gallery gallery);
	public boolean  DeleteGallery(int id);
	

	public List<Gallery> FindAllGalleries(); 
	
	public GalleryOwner findOwner(int id);
	
	public Gallery FindGalleryByName(String name); 

	public Gallery FindGalleryById(int id); 
	public List<Gallery> FindGalleriesByOwner(GalleryOwner owner);
	public List<Gallery> FindGalleriesByLocation(String zone);
	
	public List<Gallery> FindGalleriesByDate(String dateS , String dateE); 
	
	
	public boolean AddPlanToGallery (int id , Schedule sch) ;
	public boolean EditPlanToGallery (int id, Schedule sch) ; 
	public boolean CancelPlanToGallery (int id, int sch) ; 
	

}
