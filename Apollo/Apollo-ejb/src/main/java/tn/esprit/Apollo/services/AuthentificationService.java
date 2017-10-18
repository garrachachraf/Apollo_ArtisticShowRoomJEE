package tn.esprit.Apollo.services;

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

	public User findBy(String login, String password) {
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

}
