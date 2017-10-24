package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: Ticket
 *
 */
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
@Entity
public class Ticket implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private static final long serialVersionUID = 1L;
//    private Date ticketDate;
    private String title;
    private String description;
    private Float price;
    private Date orderDate;
//    @JsonIgnore
//	@JsonIdentityInfo
//    @JsonManagedReference(value="event")
//    @ManyToOne(fetch=FetchType.EAGER)
//   @JoinColumn(name="forumAnswerId",referencedColumnName="id",insertable=true,updatable=true,nullable=true)
//    @JsonManagedReference
//    @JsonBackReference 
//    @JoinColumn(name="event_id",referencedColumnName="id")
//    @JsonBackReference("evtik")
    @ManyToOne
    private Event event;
//    @JsonBackReference 
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Ticket() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Date getTicketDate() {
//		return ticketDate;
//	}
//	public void setTicketDate(Date ticketDate) {
//		this.ticketDate = ticketDate;
//	}
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}   
}
