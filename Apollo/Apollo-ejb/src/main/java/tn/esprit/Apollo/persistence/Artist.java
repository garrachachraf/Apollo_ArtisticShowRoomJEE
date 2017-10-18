package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: Artist
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Artist")
public class Artist extends GalleryOwner implements Serializable {
	@OneToMany(mappedBy="artist",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST )
	private List<Follow> followers;
    @OneToMany(mappedBy="artist",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST )
    private List<ArtWork> artworks;
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST )
	private List<ShowRoom> showrooms; 
    private ArtistType type;
    public List<ShowRoom> getShowrooms() {
		return showrooms;
	}
	public void setShowrooms(List<ShowRoom> showrooms) {
		this.showrooms = showrooms;
	}
@Transient
	private static final long serialVersionUID = 1L;

	public Artist() {
		super();
	}
    @Enumerated(EnumType.STRING)
	public ArtistType getType() {
		return type;
	}

	public void setType(ArtistType type) {
		this.type = type;
	}
   
}
