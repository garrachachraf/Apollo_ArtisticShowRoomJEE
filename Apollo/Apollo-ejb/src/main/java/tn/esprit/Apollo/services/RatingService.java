package tn.esprit.Apollo.services;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.Rating;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class RatingService implements RatingServiceLocal,RatingServiceRemote{
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public float getAverageRating(int artworkId) {
		return (float)(em.createQuery("SELECT AVG(r.value) FROM Rating r WHERE r.artwork.id = :artworkId ")
				.setParameter("artworkId", artworkId)).getSingleResult();
	}

	@Override
	public void addRating(int artworkId, float value,User user) {
		Rating myRating = findByArtworkAndUser(artworkId,user.getId());
		if(myRating != null) {
			myRating.setRatingValue(value);
			myRating.setRatingDate(new Date());
			em.merge(myRating);
		}else {
			Rating r = new Rating();
			r.setRatingValue(value);
			r.setUser(user);
			r.setArtWork(em.find(ArtWork.class, artworkId));
			r.setRatingDate(new Date());
			em.persist(r);	
		}

	}

	@Override
	public Rating findByArtworkAndUser(int artworkId ,int userId) {
		return (Rating)(em.createQuery("SELECT r FROM Rating r WHERE r.artwork.id = :artworkId AND r.user.id = :userId")
				.setParameter("artworkId", artworkId)
				.setParameter("userId", userId)).getSingleResult();
	}

}
