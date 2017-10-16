package tn.esprit.Apollo.services;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.WhishList;;

@Stateless
@LocalBean
public class WishListService implements WishListServiceLocal, WishListServiceRemote{
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void addItem(int itemId) {
		ArtWork artwork =em.find(ArtWork.class, itemId);
		WhishList wishList =getWishList();
		ArrayList<ArtWork> artworks = (ArrayList<ArtWork>) wishList.getArtWorks();
		artworks.add(artwork);
		wishList.setArtWorks(artworks);
	}

	@Override
	public void deleteItem(int itemId) {
		ArtWork artwork =em.find(ArtWork.class, itemId);
		WhishList wishList =getWishList();
		ArrayList<ArtWork> artworks = (ArrayList<ArtWork>) wishList.getArtWorks();
		artworks.remove(artwork);
		wishList.setArtWorks(artworks);
		
	}

	@Override
	public double getTotal() {
		return (double)(em.createQuery("SELECT SUM(a.price) FROM ArtWork a WHERE a.artist.id = :userId ")
				.setParameter("userId", 1)).getSingleResult();
	}

	@Override
	public WhishList getWishList() {
		// get wishList of current user
		return null;
	}

}
