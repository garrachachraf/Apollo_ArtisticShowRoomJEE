package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import javax.persistence.*;



@Embeddable
public class RatingPk implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int idUser;
	private int idArt;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArt;
		result = prime * result + idUser;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingPk other = (RatingPk) obj;
		if (idArt != other.idArt)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdArt() {
		return idArt;
	}
	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}
	

   
}
