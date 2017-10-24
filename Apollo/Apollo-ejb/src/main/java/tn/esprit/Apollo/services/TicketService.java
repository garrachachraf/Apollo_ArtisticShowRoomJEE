package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Ticket;

@Stateless
@LocalBean
public class TicketService implements TicketServiceRemote {

	@PersistenceContext
	private EntityManager em;
	private Ticket ticket = new Ticket();
	private List<Ticket> listeTicket;
	
	
	@Override
	public Ticket createTicket(Ticket ticket) {
		em.persist(ticket);
		em.flush();
		return ticket;
	}

	@Override
	public List<Ticket> readTicket() {
		return (em.createQuery("SELECT t FROM Ticket t")).getResultList();
	}

	@Override
	public void updateTicket(Ticket ticket) {
		em.merge(ticket);
	}

	@Override
	public void deleteTicket(int id) {
		Ticket ticket = em.find(Ticket.class, id);
		em.remove(ticket);
		
	}

	@Override
	public Ticket findById(int id) {
		return em.find(Ticket.class, id);
	}

	@Override
	public boolean FindTicketExist(int id) {
		if(em.find(Ticket.class, id)!=null)
		return true;		
		return false;
	}

	@Override
	public List<Ticket> readTicketsOfEvent(int id) {
		return em.createQuery("select t from Ticket t where t.event.id = :id").setParameter("id", id).getResultList();
	}
//
//	public List<Ticket> readTickets(int id) {
//	return em.createQuery("select t from Ticket t where t.event_id = :id").setParameter("id", id).getResultList();
//	}
}
