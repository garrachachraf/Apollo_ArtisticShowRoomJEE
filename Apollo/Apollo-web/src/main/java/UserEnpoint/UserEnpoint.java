package UserEnpoint;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;
import tn.esprit.Apollo.services.AuthentificationService;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@LocalBean
@Stateless
public class UserEnpoint {
	@EJB
	private AuthentificationService User;
	@Context
	UriInfo uriInfo;
	Logger logger = Logger.getLogger("test");

	@POST
	@Path("/login")
	@Consumes(APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("login") String login, @FormParam("password") String password) {
		try {

			 //Authenticate the user using the credentials provided
			authenticate(login, password);
			 //Issue a token for the user
			String token = issueToken(login);
			 //Return the token on the response
			return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

		} catch (Exception e) {
			System.out.println("" + e);
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private String issueToken(String login) {
		Key key = MacProvider.generateKey();
		String secret = "maissen";
		String base64Encoded = TextCodec.BASE64.encode(key.getEncoded());
		logger.info("####  user point : " + base64Encoded);
		System.out.println(base64Encoded);
		String jwtToken = Jwts.builder().setSubject(login).setIssuer(uriInfo.getAbsolutePath().toString())
				.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		return jwtToken;
	}

	 //algo needed for token generation
	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	 //just validating the password and login
	private void authenticate(String login, String password) throws Exception {
		if (User.findBy(login, password) == null)
			throw new SecurityException("Invalid user/password");
	}

}