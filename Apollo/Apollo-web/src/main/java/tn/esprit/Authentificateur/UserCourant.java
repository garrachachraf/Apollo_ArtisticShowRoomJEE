package tn.esprit.Authentificateur;

import io.jsonwebtoken.Jwts;

public class UserCourant {
	
	
	




public static Boolean verifyToken(String token){
		String s = "maissen";
		String id= Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getId();
		if(id!= null)return true;
		  return false;
		
	
	}
}
