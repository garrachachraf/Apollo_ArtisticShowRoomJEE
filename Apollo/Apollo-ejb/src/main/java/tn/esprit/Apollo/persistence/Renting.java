package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Renting
 *
 */
@Entity

public class Renting implements Serializable {
	

	@EmbeddedId
	 private RentingPk rentingPk;   
	 @ManyToOne
		@JoinColumn(name="galleryId",referencedColumnName="id",insertable=false,updatable=false)
	    private Gallery gallery ;
		@ManyToOne
		@JoinColumn(name="artistId",referencedColumnName="id",insertable=false,updatable=false)
		private Artist artist;
	
	private static final long serialVersionUID = 1L;

	public Renting() {
		super();
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
