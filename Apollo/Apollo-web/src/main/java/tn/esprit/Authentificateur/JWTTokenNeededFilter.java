package tn.esprit.Authentificateur;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	Logger logger = Logger.getLogger("test");

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		JWTTokenNeeded jwt = method.getAnnotation(JWTTokenNeeded.class);
		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		logger.info("#### authorizationHeader : " + authorizationHeader);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			logger.severe("#### invalid authorizationHeader : " + authorizationHeader);
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length() + 1).trim();

		try {

			// Validate the token

			String s = "maissen";
			if (jwt != null) {
				String[] roles = jwt.role();

				for (String a : roles) {
					System.out.println("role by request " + a);
					Boolean roleRes = Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getAudience()
							.equals(a);
					
					System.out.println("role by token " + roleRes);
					if (roleRes == true) {
						System.out.println(Jwts.parser().setSigningKey(s).parseClaimsJws(token).getBody().getAudience());
						logger.info("#### valid token : " + token);

					} else {
						logger.severe("#### invalid token : " + token);
						requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
					}

				}

			}

		} catch (Exception e) {
			logger.severe("#### invalid token : " + e + token);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

}
