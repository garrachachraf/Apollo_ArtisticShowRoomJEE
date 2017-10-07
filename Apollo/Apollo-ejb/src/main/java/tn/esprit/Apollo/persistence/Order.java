package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity

public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	private static final long serialVersionUID = 1L;
	private Date orderDate;
	private float amount;
	@OneToMany
    private List<ArtWork> ArtWorks;
    
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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public List<ArtWork> getArtWorks() {
		return ArtWorks;
	}
	public void setArtWorks(List<ArtWork> artWorks) {
		ArtWorks = artWorks;
	}
}
