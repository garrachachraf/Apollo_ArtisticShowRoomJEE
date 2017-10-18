package tn.esprit.Apollo.services;

import tn.esprit.Apollo.persistence.Rating;
import tn.esprit.Apollo.persistence.User;

public interface RatingServiceLocal {
	
	float getAverageRating(int artworkId);

	Rating findByArtworkAndUser(int artworkId, int userId);

	void addRating(int artworkId, float value, User user);
}
