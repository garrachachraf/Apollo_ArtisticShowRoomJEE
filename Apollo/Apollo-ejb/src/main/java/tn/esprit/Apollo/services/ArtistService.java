package tn.esprit.Apollo.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Artist;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class ArtistService implements ArtistServiceLocal,ArtistServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public Artist CreateUser(Artist u) {
		// TODO Auto-generated method stub
		u.setPassword(UserService.MD5It(u.getPassword()));
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public void UpdateUser(Artist u) {
		// TODO Auto-generated method stub
		u.setPassword(UserService.MD5It(u.getPassword()));
		em.merge(u);
	}

	@Override
	public void DeleteUser(int id) {
		// TODO Auto-generated method stub
		Artist artist = em.find(Artist.class, id);
		em.remove(artist);
	}

	@Override
	public Artist FindUserById(int id) {
		// TODO Auto-generated method stub
		Artist artist = em.find(Artist.class, id);
		return artist;
	}

	@Override
	public Artist FindUserByUsername(String username) {
		// TODO Auto-generated method stub
		List<Artist> lu = (em.createQuery("SELECT u FROM User u WHERE u.userName = :UName")
				.setParameter("UName", username)).getResultList(); 
		if (lu.isEmpty() ) {
			return null;	
		}
		return lu.get(0);
	}

	@Override
	public List<Artist> GetAllUsers() {
		// TODO Auto-generated method stub
		return (em.createQuery("SELECT u FROM User u WHERE role='Artist'")).getResultList();
	}

	@Override
	public boolean loginCheck(String UserName, String Password) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
