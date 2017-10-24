package tn.esprit.Apollo.services;

import javax.ejb.Local;

import tn.esprit.Apollo.persistence.Media;

@Local
public interface MediaServiceLocal {

	Media AddMediafile(Media m);
	void UpdateMedia(Media m);
	void DeleteMedia(int id);
	Media FindMedia (int id);
	Media FindByPath(String path);
}
