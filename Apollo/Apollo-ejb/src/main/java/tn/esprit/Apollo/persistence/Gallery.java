package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;;

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


		return events;
	}
		this.events = events;
	}

		return album;
	}
		this.album = album;
	}


		return calendar;
	}
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