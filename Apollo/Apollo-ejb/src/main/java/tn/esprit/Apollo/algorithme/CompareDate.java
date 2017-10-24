package tn.esprit.Apollo.algorithme;
import java.util.Calendar;
import java.util.Date;
 
public class CompareDate {
 
	public static int compareTwoDates(Date startDate, Date endDate) {
		Date sDate = getZeroTimeDate(startDate);
		Date eDate = getZeroTimeDate(endDate);
		if (sDate.before(eDate))
		{
			System.out.println("Start date is before end date");	
			return -1 ;
		}
		if (sDate.after(eDate))
		{
			System.out.println("Start date is after end date");
			return 1 ;
		}
			System.out.println("Start date and end date are equal");
			return 0 ;
	}
 
	private static Date getZeroTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
 
}