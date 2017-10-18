package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Follow
 *
 */
@Entity

public class Follow implements Serializable {

	@EmbeddedId
	private FollowPk followPk;
	private static final long serialVersionUID = 1L;
	private Date followDate;
	

	@ManyToOne
	@JoinColumn(name="artistId",referencedColumnName="id",insertable=false,updatable=false)
    private Artist artist;
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	
	
	public FollowPk getFollowPk() {
		return followPk;
	}




	public void setFollowPk(FollowPk followPk) {
		this.followPk = followPk;
	}




	public Artist getArtist() {
		return artist;
	}




	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getFollowDate() {
		return followDate;
	}




	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}




	public Follow() {
		super();
	}
   
}
