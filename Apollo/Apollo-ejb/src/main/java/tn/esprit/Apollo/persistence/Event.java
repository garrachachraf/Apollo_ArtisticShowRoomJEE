package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Entity implementation class for Entity: Event
 *
 */

@Entity
public class Event implements Serializable {

	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private Date creationDate;
	private Date startDate;
	private Date endDate;
	private Integer capacity;
	@ManyToOne
	private Gallery gallery;
	@JsonIgnore
	@OneToMany(mappedBy="event",fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private Set<Ticket> tickets;
	

	public Set<Ticket> getTickets() {
		return tickets;
	}


	public Event(Integer id, String title, String description, Date creationDate, Date startDate, Date endDate,
			Integer capacity, Gallery gallery, Set<Ticket> tickets) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.gallery = gallery;
		this.tickets = tickets;
	}


	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	






@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", description=" + description + ", creationDate="
				+ creationDate + ", startDate=" + startDate + ", endDate=" + endDate + ", capacity=" + capacity
			 + ", gallery=" + gallery + ", tickets=" + tickets + "]";
	}


	public Gallery getGallery() {
		return gallery;
	}


	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}


	public Event() {
		super();
	}   
	
   
}
