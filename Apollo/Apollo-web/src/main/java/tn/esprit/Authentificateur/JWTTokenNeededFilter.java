package tn.esprit.Authentificateur;

import java.io.IOException;
import java.security.Key;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

	
	   
	    Logger logger = Logger.getLogger("test");
	 
	    @Override
	    public void filter(ContainerRequestContext requestContext) throws IOException {
	 
	        // Get the HTTP Authorization header from the request
	        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	        logger.info("#### authorizationHeader : " + authorizationHeader);
	        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
	            logger.severe("#### invalid authorizationHeader : " + authorizationHeader);
	            throw new NotAuthorizedException("Authorization header must be provided");
	        }
	       
	        // Extract the token from the HTTP Authorization header
	        String token = authorizationHeader.substring("Bearer".length()+1).trim();
	        
	        try {
	 
	            // Validate the token
	        	Key key = MacProvider.generateKey();
	        	String s ="maissen";
	        	String base64Encoded = TextCodec.BASE64.encode(key.getEncoded());
	        	 logger.info("#### token point: "+base64Encoded);
	        	
	            Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getSubject();
	            System.out.println(token);
	            logger.info("#### valid token : "+token);
	 
	        } catch (Exception e) {
	        	logger.severe("#### invalid token : "+e+ token);
	            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	        }
	    }
	
}
