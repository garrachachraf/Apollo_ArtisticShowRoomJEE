package tn.esprit.Apollo.persistence;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ArtWork
 *
 */
@Entity

public class ArtWork implements Serializable {

	   
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int id;
   private static final long serialVersionUID = 1L;
   private String title,descreption;
   @Temporal(TemporalType.TIMESTAMP)
   private Date releaseDate,uploadDate;
   private String mediaPath;
   @ManyToOne
   private Artist artist;
   @OneToOne(mappedBy="artWork")
   private Media media;
 




public String getMediaPath() {
	return mediaPath;
}
    @OneToMany(mappedBy = "artWork")
	private List<Rating> ratings;


public void setMediaPath(String mediaPath) {
	this.mediaPath = mediaPath;
}
private float price;
   private ArtWorkCategory category;
   
   
   public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
