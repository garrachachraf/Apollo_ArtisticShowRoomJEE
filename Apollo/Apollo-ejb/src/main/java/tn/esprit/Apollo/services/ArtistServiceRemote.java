package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Artist;
import tn.esprit.Apollo.persistence.User;

@Remote
public interface ArtistServiceRemote {
	Artist CreateUser(Artist u);
	void UpdateUser(Artist u);
	void DeleteUser(int id);
	Artist FindUserById(int id);
	Artist FindUserByUsername(String username);
	List<Artist> GetAllUsers();
	boolean loginCheck(String UserName , String Password);
}
