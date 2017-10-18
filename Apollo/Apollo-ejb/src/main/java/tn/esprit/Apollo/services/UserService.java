package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.User;





@Stateless
@LocalBean
public class UserService implements UserServiceLocal , UserServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public User CreateUser(User u) {
		// TODO Auto-generated method stub
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public void UpdateUser(User u) {
		// TODO Auto-generated method stub
		em.merge(u);
		
	}

	@Override
	public void DeleteUser(int id) {
		// TODO Auto-generated method stub
		User user = em.find(User.class, id);
		em.remove(user);
	}

	@Override
	public User FindUserById(int id) {
		// TODO Auto-generated method stub
		User user = em.find(User.class, id);
		return user;
	}

	@Override
	public List<User> GetAllUsers() {
		// TODO Auto-generated method stub
		return (em.createQuery("SELECT u FROM User u")).getResultList();
	}

	@Override
	public boolean loginCheck(String UserName, String Password) {
		// TODO Auto-generated method stub
		List<User> lu = (em.createQuery("SELECT u FROM User u WHERE u.userName = :UName")
				.setParameter("UName", UserName)).getResultList(); 
		if (lu.isEmpty() || !lu.get(0).getPassword().equals(Password)) {
			return false;	
		}
		return true;
		
	}

	@Override
	public User FindUserByUsername(String username) {
		// TODO Auto-generated method stub
		List<User> lu = (em.createQuery("SELECT u FROM User u WHERE u.userName = :UName")
				.setParameter("UName", username)).getResultList(); 
		if (lu.isEmpty() ) {
			return null;	
		}
		return lu.get(0);
	}

}
