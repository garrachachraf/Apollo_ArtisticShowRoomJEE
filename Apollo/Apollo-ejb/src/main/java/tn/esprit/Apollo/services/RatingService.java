package tn.esprit.Apollo.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.Rating;
import tn.esprit.Apollo.persistence.RatingPk;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class RatingService implements RatingServiceLocal,RatingServiceRemote{
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public double getAverageRating(int artworkId) {
		try {
			return (double)(em.createQuery("SELECT AVG(r.ratingValue) FROM Rating r WHERE r.artWork.id = :artworkId ")
					.setParameter("artworkId", artworkId)).getSingleResult();
		}
		catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void addRating(int artworkId, float value,User user) {
		Rating myRating = findByArtworkAndUser(artworkId,user.getId());
		if(myRating != null) {
			myRating.setRatingValue(value);
			myRating.setRatingDate(new Date());
			em.merge(myRating);
		}else {
			ArtWork artwork = em.find(ArtWork.class, artworkId);
			Rating r = new Rating();
			RatingPk rp = new RatingPk();
			rp.setIdArt(artwork.getId());
			rp.setIdUser(user.getId());
			r.setRatingPk(rp);
			r.setRatingValue(value);
			r.setUser(user);
			r.setArtWork(artwork);
			r.setRatingDate(new Date());
			em.persist(r);	
		}

	}

	@Override
	public Rating findByArtworkAndUser(int artworkId ,int userId) {
		List<Rating> r = (List<Rating>)(em.createQuery("SELECT r FROM Rating r WHERE r.artWork.id = :artworkId AND r.user.id = :userId")
				.setParameter("artworkId", artworkId)
				.setParameter("userId", userId)).getResultList();
		if(r.isEmpty())
			return null;
		else
			return r.get(0);
	}

}
