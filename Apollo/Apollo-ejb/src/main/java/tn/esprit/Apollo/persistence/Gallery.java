package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.Apollo.loggerListener.GalleryLoggerListener;

@Entity
@EntityListeners(GalleryLoggerListener.class)
public class Gallery implements Serializable {


	 private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;	
	 private String name;
	 private Integer maxCapacity ;
	 @Embedded 
	 private Marker location;
	 @Embedded 
     private Pricing pricing ;
	 private Double surface;
	 private String description;
	 @ManyToOne
     private GalleryOwner galleryOwner;
	 
	 @JsonIgnore
     @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
     private Set<Event> events;	
	 
 	 @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
     private Set<Media>album;
 	 
 	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch=FetchType.EAGER)
     private Set<Schedule> calendar ;
	 
 	
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
	
	
	public Marker getLocation() {
		return location;
	}
	public void setLocation(Marker location) {
		this.location = location;
	}



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



	public GalleryOwner getGalleryOwner() {
		return galleryOwner;
	}
	public void setGalleryOwner(GalleryOwner galleryOwner) {
		this.galleryOwner = galleryOwner;
	}


	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}



	public Set<Media> getAlbum() {
		return album;
	}
	public void setAlbum(Set<Media> album) {
		this.album = album;
	}


	

	public Set<Schedule> getCalendar() {
		return calendar;
	}
	public void setCalendar(Set<Schedule> calendar) {
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
	
	@Override
	public String toString() {
		return this.name+" "+this.description+" "+this.maxCapacity+" "+this.location.getAddress();
	}
	
}