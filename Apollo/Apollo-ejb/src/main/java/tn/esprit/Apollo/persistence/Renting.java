package tn.esprit.Apollo.persistence;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import tn.esprit.Apollo.loggerListener.RentingLoggerListener;

/**
 * Entity implementation class for Entity: Renting
 *
 */
@Entity

@EntityListeners(RentingLoggerListener.class)
public class Renting implements Serializable {

	@EmbeddedId
	private RentingPk rentingPk;

	@ManyToOne
	@JoinColumn(name = "galleryId", referencedColumnName = "id", insertable = false, updatable = false)
	private Gallery gallery;

	@ManyToOne
	@JoinColumn(name = "artistId", referencedColumnName = "id", insertable = false, updatable = false)
	private Artist artist;

	private static final long serialVersionUID = 1L;

	public Renting() {
		super();
	}

	public Renting(Gallery gallery, Artist artist) {
		super();
		this.gallery = gallery;
		this.artist = artist;
		this.rentingPk = new RentingPk(gallery.getId(), artist.getId());
	}

	public RentingPk getRentingPk() {
		return rentingPk;
	}

	public void setRentingPk(RentingPk rentingPk) {
		this.rentingPk = rentingPk;
	}

	public Gallery getGallery() {
		return gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
}
