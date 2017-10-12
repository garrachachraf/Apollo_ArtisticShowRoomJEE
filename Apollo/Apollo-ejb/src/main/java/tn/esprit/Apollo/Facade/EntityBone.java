package tn.esprit.Apollo.Facade;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
//bone for our entitys (auto.id)

@MappedSuperclass
public abstract class EntityBone implements Serializable{
		@Transient public static final long serialVersionUID = 196919661993L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	

}
