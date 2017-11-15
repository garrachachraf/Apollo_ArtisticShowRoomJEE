package tn.esprit.Apollo.persistence;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Admin")
public class Admin extends User implements Serializable{
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
}
