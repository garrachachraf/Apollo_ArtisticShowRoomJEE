package tn.esprit.Apollo.persistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.Apollo.Facade.EntityBone;

// can use @Table here to specify table name
@Entity
@DiscriminatorValue("user")
@DiscriminatorColumn(name = "role")
public class User extends EntityBone {

	/**
	 * 
	 */
	@Transient private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String email;
	@Column(unique=true)
	private String userName;
	private String password;
	private String gender;
	private String imagePath;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	@Column(name = "role", insertable=false, updatable=false, nullable = false)
    private String role;    
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Follow> followings;
	@OneToMany(mappedBy = "user")
	private List<Notification> notifications;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Rating> ratings;
	@OneToOne
	private WhishList whishList;
	@OneToOne
	private Collection collection;
	@OneToOne(mappedBy = "user")
	private Media avatar;

	public WhishList getWhishList() {
		return whishList;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public void setWhishList(WhishList whishList) {
		this.whishList = whishList;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@JsonIgnore
	@Transient
	public String getDecriminatorValue() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



}