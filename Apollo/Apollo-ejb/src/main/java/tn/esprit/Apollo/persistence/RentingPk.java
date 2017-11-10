package tn.esprit.Apollo.persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Entity implementation class for Entity: RentingPk
 *
 */
@Embeddable

public class RentingPk implements Serializable {
	private int galleryId;
	private int artistId;
	private static final long serialVersionUID = 1L;

	public RentingPk() {
		super();
	}

	public RentingPk(int galleryId, int artistId) {
		super();
		this.galleryId = galleryId;
		this.artistId = artistId;
	}

	public int getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(int galleryId) {
		this.galleryId = galleryId;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artistId;
		result = prime * result + galleryId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentingPk other = (RentingPk) obj;
		if (artistId != other.artistId)
			return false;
		if (galleryId != other.galleryId)
			return false;
		return true;
	}

}
