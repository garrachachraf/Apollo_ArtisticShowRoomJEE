package tn.esprit.Authentificateur;

import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;

import io.jsonwebtoken.Jwts;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.UserServiceLocal;

public class UserCourant {
	
	@EJB
	UserServiceLocal UserService ;
	
	
public UserCourant(UserServiceLocal userService) {
		super();
		
	}


public User usernameToken(String authorizationHeader){
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
		String s = "maissen";
		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length() + 1).trim();
		String id=Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getId();
		return UserService.FindUserById(Integer.valueOf(id));
	}
}
