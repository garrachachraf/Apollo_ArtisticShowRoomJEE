package tn.esprit.Apollo.persistence;



import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import tn.esprit.Apollo.Facade.*;
/**
 * Entity implementation class for Entity: ArtWork
 *
 */
@Entity
public class ArtWork extends EntityBone {
   @Transient private static final long serialVersionUID = 1L;
   private String title,descreption;
   @Temporal(TemporalType.TIMESTAMP)
   private Date releaseDate,uploadDate;
   private String mediaPath;
   @ManyToOne(fetch=FetchType.EAGER)
   private Artist artist;

   @OneToOne(mappedBy="artWork",cascade=CascadeType.ALL)
   private Media media;
   @OneToMany(mappedBy = "artWork",cascade=CascadeType.PERSIST )
   @JsonManagedReference
   private List<Rating> ratings;
   private float price;
   private ArtWorkCategory category;
   
  
	

public String getMediaPath() {
	return mediaPath;
}
public void setMediaPath(String mediaPath) {
	this.mediaPath = mediaPath;
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescreption() {
	return descreption;
}
public void setDescreption(String descreption) {
	this.descreption = descreption;
}
public Date getReleaseDate() {
	return releaseDate;
}
public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
}
public Date getUploadDate() {
	return uploadDate;
}
public void setUploadDate(Date uploadDate) {
	this.uploadDate = uploadDate;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public ArtWorkCategory getCategory() {
	return category;
}
public void setCategory(ArtWorkCategory category) {
	this.category = category;
}
public List<Rating> getRatings() {
	return ratings;
}
public void setRatings(List<Rating> ratings) {
	this.ratings = ratings;
}
	
   
}
