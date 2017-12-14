package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Artist;
import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class GalleryOwnerService implements GalleryOwnerServiceLocal , GalleryOwnerServiceRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public GalleryOwner CreateUser(GalleryOwner u) {
		// TODO Auto-generated method stub
		u.setPassword(UserService.MD5It(u.getPassword()));
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public void UpdateUser(GalleryOwner u) {
		// TODO Auto-generated method stub
		if(u.getPassword() != null){
			u.setPassword(UserService.MD5It(u.getPassword()));
		}
		else {
			u.setPassword(FindUserById(u.getId()).getPassword());
		}
		em.merge(u);
	}

	@Override
	public List<GalleryOwner> GetAllUsers() {
		// TODO Auto-generated method stub
		return (em.createQuery("SELECT u FROM User u WHERE role='GalleryOwner'")).getResultList();
	}

	@Override
	public GalleryOwner FindUserById(int id) {
		// TODO Auto-generated method stub
		GalleryOwner go = em.find(GalleryOwner.class, id);
		return go;
	}

}
