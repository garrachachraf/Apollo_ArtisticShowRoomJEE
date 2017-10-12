package tn.esprit.Apollo.services;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tn.esprit.Apollo.persistence.Collection;
@Stateless
@LocalBean
public class CollectionService extends AbstractFacade<Collection> implements CollectionServiceRemote {

	public CollectionService() {
		super(Collection.class);
		// TODO Auto-generated constructor stub
	}

}
