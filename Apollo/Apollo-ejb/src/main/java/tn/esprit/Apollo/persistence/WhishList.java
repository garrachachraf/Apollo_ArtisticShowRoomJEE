package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.Apollo.loggerListener.WhishListLoggerListener;

/**
 * Entity implementation class for Entity: WhishList
 *
 */
@Entity
@EntityListeners(WhishListLoggerListener.class)

public class WhishList implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@OneToMany
    private List<ArtWork> artWorks;
	public List<ArtWork> getArtWorks() {
		return artWorks;
	}

	public void setArtWorks(List<ArtWork> artWorks) {
		this.artWorks = artWorks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


   
}
