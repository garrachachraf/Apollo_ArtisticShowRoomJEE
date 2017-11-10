package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ticket
 *
 */
@Entity
public class Ticket implements Serializable {  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private static final long serialVersionUID = 1L;
    private String title;
    private String note;
    private Float price;
    private Date orderDate;

    @ManyToOne
    private Event event;
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Ticket() {
	}
	
	
	public Ticket(Integer id, String title, String note, Float price) {
		this.id = id;
		this.title = title;
		this.note = note;
		this.price = price;
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
	public String getNote() {
		return note;
	}
	public void setNote(String description) {
		this.note = description;
	}
	@Override
	public String toString() {
		return "Ticket [title=" + title + ", note=" + note + ", price=" + price + ", orderDate="
				+ orderDate+ "]";
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
