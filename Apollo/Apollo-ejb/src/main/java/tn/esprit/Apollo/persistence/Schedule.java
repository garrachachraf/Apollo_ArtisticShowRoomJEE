package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Calendar;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Calendar
 *
 */
@Entity

public class Schedule implements Serializable {


	private Integer id;
	private String title;
	private Calendar startDate;
	private Calendar endDate;
	private Cause type ;
	
	private static final long serialVersionUID = 1L;

	public Schedule() {
		super();
	}  
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
	
	
	@Temporal(TemporalType.TIMESTAMP )
	public Calendar getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}   

	

	@Temporal(TemporalType.TIMESTAMP )
	public Calendar getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	
	@Enumerated(EnumType.STRING)
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
