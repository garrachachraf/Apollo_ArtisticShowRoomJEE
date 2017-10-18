package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Gallery implements Serializable {


	 private static final long serialVersionUID = 1L;
	 
	 private Integer id;	
	 private String name;
	 private Integer maxCapacity ;
	 private Marker location;
     private Pricing pricing ;
	 private Double surface;
	 private String description;
     private GalleryOwner galleryOwner;
     private List<Event> events;	
	 private List<Media>album;
     private List<Schedule> calendar ;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	
	

	@Embedded 
	public Marker getLocation() {
		return location;
	}
	public void setLocation(Marker location) {
		this.location = location;
	}



	@Embedded 
	public Pricing getPricing() {
		return pricing;
	}
	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}



	public Double getSurface() {
		return surface;
	}
	public void setSurface(double d) {
		this.surface = d;
	}



	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@ManyToOne
	public GalleryOwner getGalleryOwner() {
		return galleryOwner;
	}
	public void setGalleryOwner(GalleryOwner galleryOwner) {
		this.galleryOwner = galleryOwner;
	}


	@OneToMany(mappedBy="gallery")
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}


	@OneToMany(mappedBy="gallery")
	public List<Media> getAlbum() {
		return album;
	}
	public void setAlbum(List<Media> album) {
		this.album = album;
	}


	
	@OneToMany(fetch=FetchType.EAGER)
	public List<Schedule> getCalendar() {
		return calendar;
	}
	public void setCalendar(List<Schedule> calendar) {
		this.calendar = calendar;
	}   

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Gallery() {
		super();
	}
	
}