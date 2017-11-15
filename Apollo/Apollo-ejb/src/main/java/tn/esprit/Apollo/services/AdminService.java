package tn.esprit.Apollo.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Admin;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class AdminService implements AdminServiceLocal , AdminServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public Admin CreateAdmin(Admin u) {
		// TODO Auto-generated method stub
		u.setPassword(MD5It(u.getPassword()));
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public void UpdateAdmin(Admin u) {
		// TODO Auto-generated method stub
		em.merge(u);
		
	}

	@Override
	public void DeleteAdmin(int id) {
		// TODO Auto-generated method stub
		Admin user = em.find(Admin.class, id);
		em.remove(user);
	}

	@Override
	public Admin FindAdminById(int id) {
		// TODO Auto-generated method stub
		Admin user = em.find(Admin.class, id);
		return user;
	}

	@Override
	public List<Admin> GetAllAdmins() {
		// TODO Auto-generated method stub
		return (em.createQuery("SELECT u FROM Admin u")).getResultList();
	}

	@Override
	public boolean loginCheck(String UserName, String Password) {
		// TODO Auto-generated method stub
		List<Admin> lu = (em.createQuery("SELECT u FROM Admin u WHERE u.userName = :UName")
				.setParameter("UName", UserName)).getResultList(); 
		if (lu.isEmpty() || !lu.get(0).getPassword().equals(Password)) {
			return false;	
		}
		return true;
		
	}

	@Override
	public Admin FindUserByUsername(String username) {
		// TODO Auto-generated method stub
		List<Admin> lu = (em.createQuery("SELECT u FROM Admin u WHERE u.userName = :UName")
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
