package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Renting;

/**
 * Session Bean implementation class RentingService
 */
@Stateless
@LocalBean
public class RentingService implements RentingServiceRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager entityManager ;
	
    public RentingService() {
    }

	@Override
	public void CreateRenting(Renting renting) {
		entityManager.persist(renting);
		entityManager.flush();
		
	}

	@Override
	public boolean DeleteRenting(int id) {	
		
		try
		{
			Renting r = entityManager.find(Renting.class, id);
			entityManager.remove(r);
			entityManager.flush();
			return true ;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false ;
		}
	}
	

	@Override
	public List<Renting> findAllRentings() 
	{
		return (List<Renting>)(entityManager.createQuery("Select r from Renting r ").getResultList());	
	}

	@Override
	public List<Renting> findRentingByArtist(int artistId) 
	{
		return (List<Renting>)(entityManager.createQuery("Select r from Renting r where r.rentingPk.artistId =: id").setParameter("id", artistId).getResultList());	
	}

	@Override
	public List<Renting> findRentingByGallery(int galleryId) 
	{
		return (List<Renting>)(entityManager.createQuery("Select r from Renting r where r.rentingPk.galleryId =: id").setParameter("id", galleryId).getResultList());	
	}

}
