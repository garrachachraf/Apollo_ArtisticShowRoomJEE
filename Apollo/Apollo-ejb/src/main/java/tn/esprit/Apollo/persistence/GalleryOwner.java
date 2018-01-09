package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import tn.esprit.Apollo.loggerListener.GalleryOwnerLoggerListener;


/**
 * Entity implementation class for Entity: GalleryOwner
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("GalleryOwner")
@EntityListeners(GalleryOwnerLoggerListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
public class GalleryOwner extends User implements Serializable {
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL )
	private Set<Gallery> galleries;

    @Column(nullable = true)

	private int PhoneNumber;
	public int getPhoneNumber() {
		return PhoneNumber;
	}
	public Set<Gallery> getGalleries() {
		return galleries;
	}
	public void setGalleries(Set<Gallery> galleries) {
		this.galleries = galleries;
	}
	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	@Transient
	private static final long serialVersionUID = 1L;
	public GalleryOwner() {
		super();
	}
   
}
