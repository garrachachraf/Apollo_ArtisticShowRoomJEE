package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.User;

@Local
public interface GalleryOwnerServiceLocal {
	GalleryOwner CreateUser(GalleryOwner u);
	void UpdateUser(GalleryOwner u);
	List<GalleryOwner> GetAllUsers();
	GalleryOwner FindUserById(int id);

}
