package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Event;
import tn.esprit.Apollo.persistence.Ticket;

@Remote
public interface TicketServiceRemote {
	
	Ticket createTicket(Ticket ticket);
	List<Ticket> readTicket();
	void updateTicket(Ticket ticket);
	void deleteTicket(int id);
	Ticket findById(int id);
	public boolean FindTicketExist(int id);
	public List<Ticket> readTicketsOfEvent(int id);
	
	
}
