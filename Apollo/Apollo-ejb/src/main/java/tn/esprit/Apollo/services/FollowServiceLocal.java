package tn.esprit.Apollo.services;

import java.util.List;

import tn.esprit.Apollo.persistence.Artist;
import tn.esprit.Apollo.persistence.Follow;
import tn.esprit.Apollo.persistence.User;

public interface FollowServiceLocal {
	
	void follow(int artistId,int userId);
	
	void unfollow(int artistId,int userId);
	
	int countFollowers(int artistId);
	
	int countFollowings(int userId);
	
	List<User> getFollowers(int artistId);
	
	List<Artist> getFollowings(int userId);
	
	Follow findFollow(int userId, int artistId);
}