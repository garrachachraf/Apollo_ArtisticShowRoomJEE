package tn.esprit.Apollo.services;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Gallery;
import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.Schedule;

@Remote
public interface GalleryServiceRemote {
	
	public void Display ();
	
	public void CreateGalleryOwner (GalleryOwner gOwner);
	public String findOwner() ;
	public GalleryOwner findOw() ;
	public void  AddGallery(Gallery gallery);
	public boolean  EditGallery(Gallery gallery);
	public boolean  DeleteGallery(Gallery gallery);
	public List<Gallery> FindGalleriesByOwner(GalleryOwner owner);
	public List<Gallery> FindGalleriesByLocation(String zone);
	public Gallery FindGalleryByName(String name); 	
	public List<Gallery> FindGalleriesByDate(Calendar dateS , Calendar dateE); 
	public boolean AddPlanToGallery (Gallery gallery , Schedule sch) ;
	public boolean EditPlanToGallery (Gallery gallery , Schedule sch) ; 
	public boolean CancelPlanToGallery (Gallery gallery , Schedule sch) ; 
	

}
