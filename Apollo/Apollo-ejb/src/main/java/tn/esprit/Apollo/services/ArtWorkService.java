package tn.esprit.Apollo.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.Apollo.persistence.ArtWork;

@Stateless
@LocalBean
public class ArtWorkService extends AbstractFacade<ArtWork> implements ArtWorkServiceRemote {

	public ArtWorkService() {
		super(ArtWork.class);
		// TODO Auto-generated constructor stub
	}

}
