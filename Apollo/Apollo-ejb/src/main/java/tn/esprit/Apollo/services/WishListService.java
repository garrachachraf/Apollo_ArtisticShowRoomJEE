package tn.esprit.Apollo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.persistence.WhishList;;

@Stateless
@LocalBean
public class WishListService implements WishListServiceLocal, WishListServiceRemote{
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void addItem(int itemId, User user) {
		ArtWork artwork =em.find(ArtWork.class, itemId);
		WhishList wishList =user.getWhishList();
		List<ArtWork> artworks =  wishList.getArtWorks();
		artworks.add(artwork);
		wishList.setArtWorks(artworks);
		em.merge(wishList);
	}

	@Override
	public void deleteItem(int itemId, User user) {
		ArtWork artwork =em.find(ArtWork.class, itemId);
		WhishList wishList =user.getWhishList();
		List<ArtWork> artworks = wishList.getArtWorks();
		for (int i = 0; i < artworks.size(); i++) {
			if(artworks.get(i).getId() == artwork.getId()) {
				artworks.remove(i);
			}
		}
		artworks.remove(artwork);
		wishList.setArtWorks(artworks);
		em.merge(wishList);
	}

	@Override
	public double getTotal(User user) {
		double s = 0;
		for (ArtWork artwork : getWishList(user).getArtWorks()) {
			s+=artwork.getPrice();
		}
		return s;
	}

	@Override
	public WhishList getWishList(User user) {
		return user.getWhishList();
	}
	
}
