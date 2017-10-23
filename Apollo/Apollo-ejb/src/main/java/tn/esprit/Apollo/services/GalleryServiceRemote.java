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
	public void  AddGallery(Gallery gallery);
	public boolean  EditGallery(Gallery gallery);
	public boolean  DeleteGallery(Gallery gallery);
	public Gallery FindGalleryByName(String name); 	

}
