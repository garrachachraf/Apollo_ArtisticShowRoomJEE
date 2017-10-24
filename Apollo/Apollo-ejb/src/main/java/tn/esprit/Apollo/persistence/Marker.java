package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Float;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Marker
 *
 */
@Embeddable

public class Marker implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private Double longitude;
	private Double latitude;


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double d) {
		this.longitude = d;
	}   
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
   
}
