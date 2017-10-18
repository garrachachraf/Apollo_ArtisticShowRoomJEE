package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Event;
import tn.esprit.Apollo.persistence.ShowRoom;
import tn.esprit.Apollo.persistence.Ticket;

@Remote
public interface EventServiceRemote {
	Event createEvent(Event event);
	List<Event> readEvent();
	void updateEvent(Event event);
	void deleteEvent(int id);
	Event findById(int id);
	boolean FindEventExist(int id);
//	List<ShowRoom> findByArtist(int artistId);
//	List<ShowRoom> findByKeyWord(String keyWord);
	
	
//	public Event addEvent(Event event);
//	public void EditEvent(Event event);
//	public void deleteEvent (int id);
//	public boolean FindEventExist(int id);
//	public List<Event> FindByTitle(String title);
//	public Event FindById(int id);
//	public List<Event> FindAllEvents();
//	public List<Event> findAll();
	

}
