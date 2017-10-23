package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Media
 *
 */
@Entity

public class Media implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private static final long serialVersionUID = 1L;

	private MediaType type;
	private String description;
	private String path;
	@OneToOne
	private User user;
	@OneToOne
	private ArtWork artWork;
	
	
	
	
	
	public MediaType getType() {
		return type;
	}
	public void setType(MediaType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArtWork getArtWork() {
		return artWork;
	}
	public void setArtWork(ArtWork artWork) {
		this.artWork = artWork;
	}
	public Media() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
   
}
