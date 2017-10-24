package tn.esprit.Apollo.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Calendar
 *
 */
@Entity

public class Schedule implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String title;

	@Temporal(TemporalType.DATE )
	private Date startDate;

	@Temporal(TemporalType.DATE )
	private Date endDate;
	

	@Enumerated(EnumType.STRING)
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
	
	
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}   

	
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
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
