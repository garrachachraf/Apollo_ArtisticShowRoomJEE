package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Calendar
 *
 */
@Entity

public class Schedule implements Serializable {


	private Integer id;
	private String title;
	private Cause type ;
	
	private static final long serialVersionUID = 1L;

	public Schedule() {
		super();
	}  
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
		return this.startDate;
	}
		this.startDate = startDate;
	}   

	

		return this.endDate;
	}
		this.endDate = endDate;
	}

	
	public Cause getType() {
		return type;
	}
	public void setType(Cause type) {
		this.type = type;
	}
   
	@Override
	public boolean equals(Object obj) {
	
		if (obj == this) return true;
        if (!(obj instanceof Schedule)) {
            return false;
        }

        Schedule sch = (Schedule) obj;

        return sch.title.equals(title) ||
        		sch.startDate.equals(startDate)  &&
        				sch.endDate.equals(endDate);
	}
}
