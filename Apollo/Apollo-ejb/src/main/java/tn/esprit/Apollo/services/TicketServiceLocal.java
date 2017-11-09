package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Local;
import tn.esprit.Apollo.persistence.Ticket;

@Local
public interface TicketServiceLocal {
	
	Ticket createTicket(Ticket ticket);
	List<Ticket> readTicket();
	void updateTicket(Ticket ticket);
	void deleteTicket(int id);
	Ticket findById(int id);
	public boolean FindTicketExist(int id);
	List<Ticket> readTicketsOfEvent(int id);
	List<Ticket> readTicketsOfEventVip(int id);
	List<Ticket> readTicketsOfEventNormal(int id);
	
	
}
