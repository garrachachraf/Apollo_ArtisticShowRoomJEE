package tn.esprit.Apollo.Api;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.Apollo.services.NotificationServiceLocal;

@ServerEndpoint("/notif/{token}")
@Stateful
public class NotificationWS {
	static ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor(); 
	
	private static Set<Session> allSessions; 
	@EJB
	NotificationServiceLocal NotificationService ;
	@OnOpen  
	  public void ChecktokenansStart(@PathParam("token") String clientId,Session session){
		if (true) {
			
		}
	      allSessions = session.getOpenSessions();
	 
	      // start the scheduler on the very first connection
	      // to call sendTimeToAll every second   
	      if (allSessions.size()==1){   
	        timer.scheduleAtFixedRate(
	             () -> sendNotifToAll(session),0,1,TimeUnit.SECONDS);    
	      }
	     }  
	private void sendNotifToAll(Session session){       
	     allSessions = session.getOpenSessions();
	     for (Session sess: allSessions){          
	        try{   
	        	
	          sess.getBasicRemote().sendText("Notifs : "+tojson());
	          
	          } catch (IOException ioe) {        
	              System.out.println(ioe.getMessage());         
	          }   
	     }   
	  }
	private String tojson(){
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = "";
		try {
			 jsonStr = mapperObj.writeValueAsString(NotificationService.GetAllNotifs());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr ;
	}
	

}
