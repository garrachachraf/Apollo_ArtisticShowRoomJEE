package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: WhishList
 *
 */
@Entity

public class WhishList implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;
	
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
