package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: ShowRoom
 *
 */
@Entity

public class ShowRoom implements Serializable {  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;
    private String title;
    private String description;
    @JsonIgnore
	@OneToMany(mappedBy="showroom")
	List<ArtWork> artWorks;
    @JsonIgnore
	@ManyToOne
	private Artist artist;
	
	public List<ArtWork> getArtWorks() {
		return artWorks;
	}
	public void setArtWorks(List<ArtWork> artWorks) {
		this.artWorks = artWorks;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ShowRoom() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ShowRoom [id=" + id + ", title=" + title + ", description=" + description 
				+ ", artist=" + artist + "]";
	}   

   
}
