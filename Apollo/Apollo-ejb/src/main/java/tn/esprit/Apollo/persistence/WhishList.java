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
	private Integer id;
	private static final long serialVersionUID = 1L;
	private float value;
	
	@OneToMany
    private List<ArtWork> artWorks;
	public List<ArtWork> getArtWorks() {
		return artWorks;
	}

	public void setArtWorks(List<ArtWork> artWorks) {
		this.artWorks = artWorks;
	}

	

	@Temporal(TemporalType.TIMESTAMP)	
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	} 


   
}
