package tn.esprit.Apollo.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Media;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class MediaService implements MediaServiceLocal , MediaServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public Media AddMediafile(Media m) {
		em.persist(m);
		em.flush();
		return m;
	}

	@Override
	public void UpdateMedia(Media m) {
		// TODO Auto-generated method stub
		em.merge(m);
	}

	@Override
	public void DeleteMedia(int id) {
		// TODO Auto-generated method stub
		Media m = em.find(Media.class, id);
		em.remove(m);
	}

	@Override
	public Media FindMedia(int id) {
		// TODO Auto-generated method stub
		Media m = em.find(Media.class, id);
		return m;
	}

	@Override
	public Media FindByPath(String path) {
		// TODO Auto-generated method stub
		Media m = (Media) em.createQuery("SELECT m FROM Media m WHERE m.path = :paths")
				.setParameter("paths", path).getSingleResult(); 
		return m;
	}

}
