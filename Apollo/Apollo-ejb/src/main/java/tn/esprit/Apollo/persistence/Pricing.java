package tn.esprit.Apollo.persistence;

import javax.persistence.Embeddable;

@Embeddable
	
	 private Float daily;
	 private Float monthly;
	 private Float weekly;
	 private Integer minimumBooking;
	 private Float securityDeposit;
	 private Float cleaningFee;
	 
	public Float getHourly() {
		return hourly;
	}
	public void setHourly(Float hourly) {
		this.hourly = hourly;
	}
	public Float getDaily() {
		return daily;
	}
	public void setDaily(Float daily) {
		this.daily = daily;
	}
	public Float getMonthly() {
		return monthly;
	}
	public void setMonthly(Float monthly) {
		this.monthly = monthly;
	}
	public Float getWeekly() {
		return weekly;
	}
	public void setWeekly(Float weekly) {
		this.weekly = weekly;
	}
	public Integer getMinimumBooking() {
		return minimumBooking;
	}
	public void setMinimumBooking(Integer minimumBooking) {
		this.minimumBooking = minimumBooking;
	}
	public Float getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(Float securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	public Float getCleaningFee() {
		return cleaningFee;
	}
	public void setCleaningFee(Float cleaningFee) {
		this.cleaningFee = cleaningFee;
	}

}
