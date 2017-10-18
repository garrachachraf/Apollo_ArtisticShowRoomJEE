package tn.esprit.Apollo.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Gallery;
import tn.esprit.Apollo.persistence.GalleryOwner;
import tn.esprit.Apollo.persistence.Schedule;

/**
 * Session Bean implementation class GalleryService
 */
@Stateless
@LocalBean
public class GalleryService implements GalleryServiceRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager entityManger ;
	
    public GalleryService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AddGallery(Gallery gallery) {
		
			entityManger.persist(gallery);
		 
	}

	@Override
	public boolean EditGallery(Gallery gallery) {
		try 
		{
	    	entityManger.merge(gallery);
		    return true ;		
			
		} catch (Exception e) {
			System.out.println(e);
			return false ;
		}
	}

	@Override
	public boolean DeleteGallery(Gallery gallery) {
		try {
			Gallery g = entityManger.find(Gallery.class, gallery.getId());
			entityManger.remove(g);
			entityManger.flush();
			return true ;
		} catch (Exception e) {
			System.out.println(e);
			return false ;
			// TODO: handle exception
		}
		
	}

	@Override
	public List<Gallery> FindGalleriesByOwner(GalleryOwner owner) {

		try 
		{
			return (List<Gallery>)(entityManger.createQuery("Select g from Gallery g where g.galleryOwner =:searchStr").setParameter("searchStr", owner).getResultList());
		}
		catch (Exception e) 
		{
			System.out.print("find galleries by owner issue : "+e);
			return null ;
		}
			
	}
		
	

	@Override
	public List<Gallery> FindGalleriesByLocation(String zone) {
		try 
		{
			return (List<Gallery>)(entityManger.createQuery("Select g from Gallery g where  (0 < LOCATE(:searchStr, g.loction.address))").setParameter("searchStr", zone).getResultList());
		}
		catch (Exception e) 
		{
			System.out.print("find galleries by location issue : "+e);
			return null ;
		}
	}

	@Override
	public Gallery FindGalleryByName(String name) {
		try 
		{
			return (Gallery)(entityManger.createQuery("Select g from Gallery g where  g.name =:searchStr").setParameter("searchStr", name).getResultList());
		}
		catch (Exception e) 
		{
			System.out.print("find gallery by name issue : "+e);
			return null ;
		}
	}

	@Override
	public List<Gallery> FindGalleriesByDate(Calendar dateS , Calendar dateE) {

		try 
		{
		    List<Gallery> tmpGalleries ;
		    List<Gallery> galleries = null ;
		    
		    tmpGalleries = entityManger.createQuery("Select g from Gallery g where 1").getResultList();
		    for (Gallery gallery : tmpGalleries)
		    {
		    	int i =0 ;
		    	for (Schedule tmpSch : gallery.getCalendar()) {
		    		if ((dateS != tmpSch.getStartDate()) && (dateE != tmpSch.getEndDate()) )
		    			i++ ;	
				}
		    	if (gallery.getCalendar().size()==i)
		    		galleries.add(gallery);
				
			}
		    return galleries ;
		
		}
		catch (Exception e) 
		{
			System.out.print("find galleries by location issue : "+e);
			return null ;
		}
	}

	@Override
	public boolean AddPlanToGallery(Gallery gallery, Schedule sch) {
		try
		{
			 Gallery g = entityManger.find(Gallery.class, gallery.getId());
			 List<Schedule> list ;
			 list = g.getCalendar();
			 list.add(sch);
			 g.setCalendar(list);
			 entityManger.merge(g);
			 entityManger.flush();
			 return true ;
		} catch (Exception e)
		{
			System.out.println("affect plan to a gallery issue : "+e);
			return false;
			// TODO: handle exception
		}
	}

	@Override
	public boolean EditPlanToGallery(Gallery gallery, Schedule sch) {
		try
		{
		 Gallery g = entityManger.find(Gallery.class, gallery.getId());
		 List<Schedule> tmp ;
		 tmp = g.getCalendar();
		 for (Schedule s : tmp) 
		 {
			    if (s.getId()==sch.getId())
			       	s= sch ;
	    	}
		 entityManger.merge(g);
		 return true ;
	   } 
		catch (Exception e)
	{
		System.out.println("affect plan to a gallery issue : "+e);
		return false;
		// TODO: handle exception
	}
	}

	@Override
	public boolean CancelPlanToGallery(Gallery gallery, Schedule sch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Display() {
		System.out.println("Hi Waleed !");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CreateGalleryOwner(GalleryOwner gOwner) {
		try 
		{	
			entityManger.persist(gOwner);
			entityManger.flush();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findOwner() {
		GalleryOwner g = new GalleryOwner() ;
		g  = entityManger.find(GalleryOwner.class, 1);
		return g.getGender();

	}
	
	@Override
	public GalleryOwner findOw() {
		return  entityManger.find(GalleryOwner.class, 1);

	}


}
