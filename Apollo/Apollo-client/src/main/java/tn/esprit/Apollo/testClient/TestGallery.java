package tn.esprit.Apollo.testClient;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import tn.esprit.Apollo.services.GalleryServiceRemote ;

import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.algorithme.FileGenerator;
import tn.esprit.Apollo.persistence.Cause;
import tn.esprit.Apollo.persistence.Gallery;
import tn.esprit.Apollo.persistence.Marker;
import tn.esprit.Apollo.persistence.Pricing;
import tn.esprit.Apollo.persistence.Schedule;

import javax.naming.NamingException;

public class TestGallery {

	public static void main(String[] args) throws IOException {
		try 
		{
			Context context = new InitialContext();
			GalleryServiceRemote proxy = (GalleryServiceRemote) context.lookup("/Apollo-ear/Apollo-ejb/GalleryService!tn.esprit.Apollo.services.GalleryServiceRemote"); 
			

			GalleryOwner gOwner = new GalleryOwner() ;
			GalleryOwner Ow = new GalleryOwner() ;
			
			gOwner.setFirstname("Jhon");
			gOwner.setLastname("Doe");
			gOwner.setEmail("john.doe@esprit.tn");
			gOwner.setUserName("drWaleed");
			gOwner.setGender("male");
			gOwner.setCountry("American");
			gOwner.setCity("Florida");
			
			proxy.CreateGalleryOwner(gOwner);			
			Ow= proxy.findOwner(gOwner.getId());
			//
			Schedule sch = new Schedule();
			Date dateS ;
			Date dateE ;
			DateFormat formatter ;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateS = formatter.parse("2017-03-22");
			dateE = formatter.parse("2017-03-23");
			sch.setTitle("Fete de l'independance");
			sch.setType(Cause.Holiday);
			sch.setStartDate(dateS);
			sch.setEndDate(dateE);
			
			Schedule sch2 = new Schedule();
			Date dateS2 ;
			Date dateE2 ;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateS2 = formatter.parse("2017-05-10");
			dateE2 = formatter.parse("2017-05-14");
			sch2.setTitle("Fuite");
			sch2.setType(Cause.Renovations);
			sch2.setStartDate(dateS2);
			sch2.setEndDate(dateE2);
			//
			Marker gLocation = new Marker();
			gLocation.setAddress("45 avenue habib bourguiba cité el ghazela Ariana 2080 , Tunisia");
			gLocation.setLatitude(36.422329);
			gLocation.setLongitude(11.08430550000003);
			//
			Pricing gPricing = new Pricing();
			gPricing.setCleaningFee((float) 80);
			gPricing.setMinimumBooking(3);
			gPricing.setHourly((float)200);
			gPricing.setSecurityDeposit((float)150);
			gPricing.setMonthly((float) 2800);
			//
			Gallery gallery = new Gallery();
			gallery.setName("Gallery 7");
			gallery.setDescription("this is a description");
			gallery.setMaxCapacity(150);
			gallery.setSurface(400.5);

			Set<Schedule> c = new HashSet<Schedule>();
			c.add(sch);
			c.add(sch2);
			
			gallery.setLocation(gLocation);
			gallery.setPricing(gPricing);
			gallery.setCalendar(c);
			gallery.setGalleryOwner(Ow);
			//**********************************************//
			Gallery gallery2 = new Gallery();
			gallery2.setName("Africa");
			gallery2.setDescription("this is a description");
			gallery2.setMaxCapacity(200);
			gallery2.setSurface(600);
			
			Marker gLocation2 = new Marker();
			gLocation2.setAddress("45 avenue habib bourguiba cité el ghazela Ariana 2080 , Tunisia");
			gLocation2.setLatitude(36.422329);
			gLocation2.setLongitude(10.08430);
			//
			Pricing gPricing2 = new Pricing();
			gPricing2.setCleaningFee((float) 80);
			gPricing2.setMinimumBooking(3);
			gPricing2.setHourly((float)200);
			gPricing2.setSecurityDeposit((float)150);
			gPricing2.setMonthly((float) 2800);

			gallery2.setLocation(gLocation2);
			gallery2.setPricing(gPricing2);
			gallery2.setGalleryOwner(Ow);
			
			/****************************/
		    proxy.AddGallery(gallery);
		    proxy.EditGallery(gallery);
		    
		    
		    
		    proxy.AddGallery(gallery2);
		     
		    FileGenerator myFile = new FileGenerator() ;
		    
		    List<Gallery> listTmp = new ArrayList<Gallery>();
		     listTmp = proxy.FindAllGalleries();
		     for (Gallery g : listTmp) 
		     {
		    	// myFile.trace(g.getName());
		    	 System.out.println(g.getName());	
			 }
		} catch (NamingException | ParseException e) {
			System.out.println(e);
		}

	}

}