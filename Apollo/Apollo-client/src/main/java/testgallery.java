import javax.naming.Context;
import javax.naming.InitialContext;
import tn.esprit.Apollo.services.GalleryServiceRemote ;

import tn.esprit.Apollo.persistence.*;

import javax.naming.NamingException;
public class testgallery {

	public static void main(String[] args) {
		try 
		{
			Context context = new InitialContext();
			GalleryServiceRemote proxy = (GalleryServiceRemote) context.lookup("/Apollo-ear/Apollo-ejb/GalleryService!tn.esprit.Apollo.services.GalleryServiceRemote"); 
			GalleryOwner Ow = new GalleryOwner() ;
			String h ;
			GalleryOwner gOwner = new GalleryOwner() ;
			
			gOwner.setFirstname("Waleed");
			gOwner.setLastname("Dridi");
			gOwner.setEmail("dridi.walid@esprit.tn");
			gOwner.setUserName("drWaleed");
			gOwner.setGender("male");
			gOwner.setCountry("Tunisia");
			gOwner.setCity("Ariana");
			proxy.CreateGalleryOwner(gOwner);
			
			h =proxy.findOwner() ;
			System.out.println(h);
			//
			Marker gLocation = new Marker();
			gLocation.setAddress("45 avenue habib bourguiba citÈ el ghazela Ariana 2080 , Tunisia");
			gLocation.setLatitude(37.422329);
			gLocation.setLongitude(-122.08430550000003);
			//
			Pricing gPricing = new Pricing();
			gPricing.setCleaningFee((float) 80);
			gPricing.setMinimumBooking(3);
			gPricing.setHourly((float)200);
			gPricing.setSecurityDeposit((float)150);
			gPricing.setMonthly((float) 2800);
			//
			Gallery gallery = new Gallery();
			Gallery ga = new Gallery();
			gallery.setName("Gallery 7");
			gallery.setLocation(gLocation);
			gallery.setDescription("this is a description");
			gallery.setMaxCapacity(150);
			gallery.setSurface(400.5);
			gallery.setPricing(gPricing);
			//
			proxy.AddGallery(ga);
		} catch (NamingException e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub

	}

}
