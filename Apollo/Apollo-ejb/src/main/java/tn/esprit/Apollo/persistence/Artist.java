package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import tn.esprit.Apollo.loggerListener.ArtistLoggerListener;
import tn.esprit.Apollo.loggerListener.GalleryLoggerListener;




/**
 * Entity implementation class for Entity: Artist
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Artist")
@EntityListeners(ArtistLoggerListener.class)
public class Artist extends GalleryOwner implements Serializable {
	@OneToMany(mappedBy="artist",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST )
	private List<Follow> followers;
    @OneToMany(mappedBy="artist",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST )
    @JsonManagedReference(value="usertoartwork")
    @JsonIgnore
    private List<ArtWork> artworks;
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST )
    @JsonIgnore
	private List<ShowRoom> showrooms ; 
    @Null
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
