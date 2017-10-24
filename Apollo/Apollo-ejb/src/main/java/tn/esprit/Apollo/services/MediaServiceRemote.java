package tn.esprit.Apollo.services;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Media;

@Remote
public interface MediaServiceRemote {
	Media AddMediafile(Media m);
	void UpdateMedia(Media m);
	void DeleteMedia(int id);
	Media FindMedia (int id);
	Media FindByPath(String path);

}
