package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Renting;

@Remote
public interface RentingServiceRemote {
	
	public void CreateRenting (Renting renting);
	public boolean DeleteRenting(int id);
	public List<Renting> findAllRentings();
	public List<Renting> findRentingByArtist (int artistId) ;
	public List<Renting> findRentingByGallery (int galleryId) ;
	
	
	
}
