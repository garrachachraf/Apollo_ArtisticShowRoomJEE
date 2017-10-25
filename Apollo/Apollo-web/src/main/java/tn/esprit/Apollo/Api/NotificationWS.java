package tn.esprit.Apollo.Api;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.NotAuthorizedException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.NotificationServiceLocal;
import tn.esprit.Apollo.services.UserServiceLocal;
import tn.esprit.Authentificateur.UserCourant;

@ServerEndpoint("/notif/{token}")
@Stateful
public class NotificationWS {
	@EJB
	protected
	UserServiceLocal UserService ;
	static ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor(); 
	
	private static Set<Session> allSessions; 
	@EJB
	NotificationServiceLocal NotificationService ;
	
	static HashMap<Session, String> SesToken = new HashMap<Session, String>() ;
	
	
	
	@OnOpen  
	  public void ChecktokenansStart(@PathParam("token") String token,Session session){
		if (UserCourant.verifyToken(token)) {
			allSessions = session.getOpenSessions();
			SesToken.put(session, token);
		      // start the scheduler on the very first connection
		      // to call sendTimeToAll every second   
		      if (allSessions.size()==1){   
		        timer.scheduleAtFixedRate(
		             () -> sendNotifToAll(session),0,1,TimeUnit.SECONDS);    
		      }
		}
		else {
			try {
				session.close();
				System.out.println("cloased");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	      
	     }  
	private void sendNotifToAll(Session session){       
	     allSessions = session.getOpenSessions();
	     for (Session sess: allSessions){          
	        try{   
	        	String token = SesToken.get(sess);
	          sess.getBasicRemote().sendText("Notifs : "+tojson(token));
	          
	          } catch (IOException ioe) {        
	              System.out.println(ioe.getMessage());         
	          }   
	     }   
	  }
	private String tojson(String token){
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = "";
		try {
			 jsonStr = mapperObj.writeValueAsString(NotificationService.GetAllNotifsByUserId(UserFromToken(token).getId()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr ;
	}
public User UserFromToken(String authorizationHeader){
		
	String s = "maissen";
	String id= Jwts.parser().setSigningKey(s).parseClaimsJws(authorizationHeader).getBody().getId();
		return UserService.FindUserById(Integer.valueOf(id));
	}	

}
