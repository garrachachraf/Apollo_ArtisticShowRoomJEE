package tn.esprit.Apollo.services;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tn.esprit.Apollo.persistence.Collection;
import tn.esprit.Apollo.persistence.ShowRoom;
@Stateless
@LocalBean
public class CollectionService extends AbstractFacade<Collection> implements CollectionServiceRemote {

	public CollectionService() {
		super(Collection.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Collection> findByUser(int artistId) {
		return (em.createQuery("SELECT s FROM Collection s WHERE s.user.id = :artistId")
				.setParameter("artistId", artistId)).getResultList();
	}

}
