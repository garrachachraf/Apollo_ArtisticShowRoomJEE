package tn.esprit.Apollo.services;

import tn.esprit.Apollo.persistence.Rating;
import tn.esprit.Apollo.persistence.User;

public interface RatingServiceRemote {
	
	float getAverageRating(int artworkId);

	void addRating(int artworkId, float ratingValue, User user);

	Rating findByArtworkAndUser(int artworkId, int userId);
}
