package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.Apollo.loggerListener.EventLoggerListener;

/**
 * Entity implementation class for Entity: Event
 *
 */
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
@Entity

@EntityListeners(EventLoggerListener.class)
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
	private EventStatus status;
	private String imagePath;
	@ManyToOne
	private Gallery gallery;
	@JsonIgnore
	@OneToMany(mappedBy="event",fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private Set<Ticket> tickets;
//	@JoinColumn(name = "galleryId", referencedColumnName = "id")
//	@JsonManagedReference
//	@JsonBackReference("galev")
//	@OneToMany(fetch=FetchType.EAGER)
//	@OneToMany(orphanRemoval=true) les enfs
//	@JsonIgnore
//	@JsonIdentityInfo
//	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
//	@JsonBackReference(value="tickets")
//	@OneToMany(mappedBy="event", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
//	@JsonManagedReference("evtik")

	

	public Set<Ticket> getTickets() {
		return tickets;
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
	
	public EventStatus getStatus() {
		return status;
	}
	public void setStatus(EventStatus status) {
		this.status = status;
	}



	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

//	@JsonProperty(value = "galleryid")
//	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//	@JsonIdentityReference(alwaysAsId = true) 
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
