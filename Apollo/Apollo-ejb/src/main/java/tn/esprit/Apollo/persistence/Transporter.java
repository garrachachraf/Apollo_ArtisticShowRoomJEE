package tn.esprit.Apollo.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import tn.esprit.Apollo.loggerListener.ArtistLoggerListener;
import tn.esprit.Apollo.loggerListener.GalleryLoggerListener;



@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("transporter")
public class Transporter extends User implements Serializable {
	
	public Transporter() {
		super();
		// TODO Auto-generated constructor stub
	}


/**
 * Entity implementation class for Entity: Artist
 *
 */






   
}
