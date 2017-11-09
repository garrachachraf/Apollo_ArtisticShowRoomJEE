package tn.esprit.Apollo.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Event;


/**
 * Session Bean implementation class EventService
 */
@Stateless
@LocalBean
public class EventService implements EventServiceRemote {
	
	/**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
	private Event event = new Event();
	private List<Event> listeEvent;
	
	@Override
	public Event createEvent(Event event) {
//		Ticket ticket = new Ticket();
//		ticket.setId(4);
//		ticket.setEvent(event);
//		ticket.setTitle("Hey");
//		List<Ticket>  tickets = null ;
//		tickets.add(ticket);
//		event.setTickets(tickets);
		em.persist(event);
		em.flush();
		return event;
	}
	@Override
	public List<Event> readEvent() {
		return (em.createQuery("SELECT e FROM Event e")).getResultList();
	}
	@Override
	public void updateEvent(Event event) {
		em.merge(event);
	}
	@Override
	public void deleteEvent(int id) {
		Event event = em.find(Event.class, id);
		em.remove(event);
	}
	@Override
	public Event findById(int id) {
		return em.find(Event.class, id);
	}
	
	@Override
	public boolean FindEventExist(int id) {
		if(em.find(Event.class, id)!=null)
		return true;		
		return false;
	}
	


	@Override
	public List<Event> findByTerm(String term) {
		return em.createQuery("SELECT e FROM Event e WHERE"
				+ " (e.description LIKE CONCAT('%',:term,'%') OR e.title LIKE CONCAT('%',:term,'%') )")
				.setParameter("term", term).getResultList();

	}
	
}
//    @Override
//	public Event addEvent(Event event) {
//		em.persist(event);
//		em.flush();
//		return event;
//	}
//
//	@Override
//	public List<Event> findAll(){
//		return (em.createQuery("SELECT e FROM Event e")).getResultList();
//	}
//
//	@Override
//	public void EditEvent(Event event) {
//		em.merge(event);
//	}
//	
//	@Override
//	public void deleteEvent(int id) {
//		Event event = em.find(Event.class, id);
//		em.remove(em.find(Event.class, id));
//		
//	}
//
//	@Override
//	public boolean FindEventExist(int id) {
//		if(em.find(Event.class, event.getId())!=null)
//		return true;		
//		return false;
//	}
//	
//	@Override
//	public List<Event> FindByTitle(String title) {
//		return em.createQuery("select e from Event e where e.title = :title").setParameter("title", title).getResultList();
//	}
//	
//	@Override
//	public Event FindById(int id) {
//		return (Event) em.createQuery("select e from Event e where e.id = :id")
//				.setParameter("id", id)
//				.getSingleResult();
//	}
//
//	@Override
//	public List<Event> FindAllEvents() 
//	{
//		return em.createQuery("select e from Event e").getResultList();
//	}
//
//}
