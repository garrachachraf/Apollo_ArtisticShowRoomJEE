package tn.esprit.Apollo.persistence;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rating
 *
 */
@Entity

public class Rating implements Serializable {
    @EmbeddedId
	private RatingPk ratingPk;   
	
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="idArt",referencedColumnName="id",insertable=false,updatable=false)
    private ArtWork artWork;
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
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

	private Date RatingDate;
    private int RatingValue;
    
    
	public Date getRatingDate() {
		return RatingDate;
	}
	public void setRatingDate(Date ratingDate) {
		RatingDate = ratingDate;
	}
	public int getRatingValue() {
		return RatingValue;
	}
	public void setRatingValue(int ratingValue) {
		RatingValue = ratingValue;
	}
	public Rating() {
		super();
	}   

   
}
