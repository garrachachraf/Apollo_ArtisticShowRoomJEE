package tn.esprit.Apollo.persistence;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import tn.esprit.Apollo.Facade.EntityBone;
import tn.esprit.Apollo.loggerListener.ArtWorkLoggerListener;

/**
 * Entity implementation class for Entity: ArtWork
 *
 */
@Entity
//@EntityListeners(ArtWorkLoggerListener.class)
public class ArtWork extends EntityBone {


	@Transient
	private static final long serialVersionUID = 1L;
	private String title, descreption;
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseDate, uploadDate;
	private String mediaPath;
	@ManyToOne
	@JsonBackReference(value="usertoartwork")
	@JsonManagedReference(value="artworktouser")
	private Artist artist;
	@OneToOne(mappedBy = "artWork", cascade = CascadeType.ALL)
	private Media media;
	@OneToMany(mappedBy = "artWork", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JsonManagedReference(value="ratingofartwork")
	private Set<Rating> ratings;
	private float price;
	private ArtWorkCategory category;
	

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescreption() {
		return descreption;
	}

	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ArtWorkCategory getCategory() {
		return category;
	}

	public void setCategory(ArtWorkCategory category) {
		this.category = category;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}


}
