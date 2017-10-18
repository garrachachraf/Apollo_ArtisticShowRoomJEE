package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Gallery implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zip_code;
	private float surface;
	private String description;
	@ManyToOne
    private GalleryOwner galleryOwner;
	@OneToMany(mappedBy="gallery")
	private List<Event> events;
	@OneToMany(mappedBy="gallery")
	private List<Media>media;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getZip_code() {
		return zip_code;
	}


	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}


	public float getSurface() {
		return surface;
	}


	public void setSurface(float surface) {
		this.surface = surface;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Gallery() {
		super();
	}   
   
}
