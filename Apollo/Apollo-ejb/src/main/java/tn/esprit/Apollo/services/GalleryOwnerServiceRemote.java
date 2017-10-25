package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.User;

@Remote
public interface GalleryOwnerServiceRemote {

	GalleryOwner CreateUser(GalleryOwner u);
	void UpdateUser(GalleryOwner u);
	List<GalleryOwner> GetAllUsers();
	GalleryOwner FindUserById(int id);

}
