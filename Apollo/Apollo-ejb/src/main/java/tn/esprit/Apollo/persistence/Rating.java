package tn.esprit.Apollo.persistence;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.Apollo.loggerListener.RatingLoggerListener;

/**
 * Entity implementation class for Entity: Rating
 *
 */
@Entity
@EntityListeners(RatingLoggerListener.class)
public class Rating implements Serializable {
	
    @EmbeddedId
    @JsonIgnore
	private RatingPk ratingPk;   
	@Transient
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="idArt",referencedColumnName="id",insertable=false,updatable=false)
	@JsonBackReference(value="ratingofartwork")
    private ArtWork artWork;
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	@JsonBackReference("user-ratings")
	private User user;
	
	public ArtWork getArtWork() {
		return artWork;
	}
	public void setArtWork(ArtWork artWork) {
		this.artWork = artWork;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	private Date ratingDate;
    private float ratingValue;
    
   
	public Date getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}
	public float getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(float ratingValue) {
		this.ratingValue = ratingValue;
	}
	public Rating() {
		super();
	}   
 
	
	public RatingPk getRatingPk() {
		return ratingPk;
	}
	public void setRatingPk(RatingPk ratingPk) {
		this.ratingPk = ratingPk;
	}

   
}
