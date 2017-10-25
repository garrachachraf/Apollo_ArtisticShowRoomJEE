package tn.esprit.Apollo.Api;

import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.HttpHeaders;

import io.jsonwebtoken.Jwts;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.UserServiceLocal;

public class getcurrentuser {
	@EJB
	UserServiceLocal UserService ;
	
	
	
	public getcurrentuser() {
		
		 UserServiceLocal userService;
	}



	public  User usernameToken(HttpHeaders header){
		String authorizationHeader = header.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
		String s = "maissen";
		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length() + 1).trim();
		String userName=Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getSubject();
		System.out.println(userName);
		
		return UserService.FindUserByUsername(userName);
	}

}
