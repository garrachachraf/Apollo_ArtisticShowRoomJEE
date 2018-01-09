package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.Apollo.loggerListener.OrderLoggerListener;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@EntityListeners(OrderLoggerListener.class)
public class Orders implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	private static final long serialVersionUID = 1L;
	private Date orderDate;
	private float totalAmount;

	@ManyToOne
	private User user;
	@OneToMany(fetch=FetchType.EAGER)
    private List<ArtWork> artWorks;
    
    public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<ArtWork> getArtWorks() {
		return artWorks;
	}
	public void setArtWorks(List<ArtWork> artWorks) {
		this.artWorks = artWorks;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setId(int id) {
		Id = id;
	}
	
}
