package tn.esprit.Apollo.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class AuthentificationService extends AbstractFacade<User> implements AuthentificationServiceRemote {

	public AuthentificationService() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}
    //find the login and password of a loging user
	public User findBy(String login, String password) {
		password = MD5It(password);
		Query q = em.createQuery("SELECT u FROM User u WHERE u.userName = :login AND u.password = :password");
		q.setParameter("login", login);
		q.setParameter("password", password);
		try {
			User user = (User) q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}

	}
	//md5 simple hach
	public String MD5It(String pass){
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
		System.out.println(generatedPassword);
		return generatedPassword ;
	}

}
