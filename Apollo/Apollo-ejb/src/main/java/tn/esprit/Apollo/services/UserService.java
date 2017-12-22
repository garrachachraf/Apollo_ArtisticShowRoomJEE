package tn.esprit.Apollo.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		u.setPassword(MD5It(u.getPassword()));
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public void UpdateUser(User u) {
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
	
	public static String MD5It(String pass){
		String generatedPassword = "";
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            
            md.update(pass.getBytes(),0,pass.length());
            generatedPassword = new BigInteger(1,md.digest()).toString(16) ;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		return generatedPassword ;
	}

}
