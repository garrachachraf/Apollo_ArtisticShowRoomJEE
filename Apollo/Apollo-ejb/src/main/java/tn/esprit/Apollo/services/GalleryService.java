package tn.esprit.Apollo.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.algorithme.CompareDate;
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
	private EntityManager entityManager ;
	
    public GalleryService() {
    }

	@Override
	public void AddGallery(Gallery gallery)
	{	
			entityManager.persist(gallery);
			entityManager.flush();
	}

	@Override
	public boolean EditGallery(Gallery gallery) {
		try 
		{
			Gallery aichouch = entityManager.find(Gallery.class, gallery.getId());
			Gallery newG = gallery ; 
			gallery = aichouch ;
			gallery.setName(newG.getName());

	    	entityManager.merge(gallery);
	    	
	    	return true ;		
			
		} catch (Exception e) {
			System.out.println(e);
			return false ;
		}
	}

	@Override
	public boolean DeleteGallery(int id) {
		try {
			Gallery g = entityManager.find(Gallery.class, id);
			entityManager.remove(g);
			entityManager.flush();
			return true ;
		} catch (Exception e) {
			System.out.println(e);
			return false ;
			// TODO: handle exception
		}
	}

	@Override
	public List<Gallery> FindGalleriesByOwner(GalleryOwner owner) 
	{
		try 
		{
			return (List<Gallery>)(entityManager.createQuery("Select g from Gallery g where g.galleryOwner =:searchStr").setParameter("searchStr", owner).getResultList());
		}
		catch (Exception e) 
		{
			System.out.print("find galleries by owner issue : "+e);
			return null ;
		}
	}
		

	public List<Gallery> FindGalleriesByLocation(String zone) {
        try 
        {
            return (List<Gallery>)(entityManager.createQuery("Select g from Gallery g where  (0 < LOCATE(:searchStr, g.location.address ))").setParameter("searchStr", zone).getResultList());
        }
        catch (Exception e) 
        {
            System.out.print("find galleries by location issue : "+e);
            return null ;
        }
    }

	public Gallery FindGalleryByName(String name) {
        try 
        {
            return (Gallery)(entityManager.createQuery("Select g from Gallery g where  g.name = :searchStr").setParameter("searchStr", name).getSingleResult());
        }
        catch (Exception e) 
        {
            System.out.print("find gallery by name issue : "+e);
            return null ;
        }
    }

	@Override
	public List<Gallery> FindGalleriesByDate(String dateS , String dateE) {
		   try
		   {
				DateFormat formatter ; 
				Date dateStart ; 
				Date dateEnd ; 
				   formatter = new SimpleDateFormat("yyyy-MM-dd");
				
					dateStart = formatter.parse(dateS);
		    		dateEnd   = formatter.parse(dateE);
				
				List<Gallery> tmpGalleries = entityManager.createQuery("Select g from Gallery g ").getResultList();
				List<Gallery> galleries = new ArrayList<Gallery>() ;
				for (Gallery gallery : tmpGalleries) 
				{
					int i =0;
					for (Schedule sch : gallery.getCalendar())
					{				
						if ((CompareDate.compareTwoDates(dateStart, sch.getStartDate())==-1)&&(CompareDate.compareTwoDates(dateEnd, sch.getStartDate())==-1))
						{
							i++ ;
						}
						else if ((CompareDate.compareTwoDates(dateStart, sch.getEndDate())==1))
						{
							i++;
						}
						else 
						{
							i--;
						}
					}
					if (gallery.getCalendar().size()==i)
						galleries.add(gallery);	
				}
				return galleries ;
	    }
		catch (ParseException e) 
		{
			   System.out.println("find by calendar error : "+e);
			   return null ;
		}
			   
	}

	@Override
	public boolean AddPlanToGallery(int id, Schedule sch) {
		try
		{
			 Gallery g = entityManager.find(Gallery.class, id);
			 Set<Schedule> list ;
			 list = g.getCalendar();
			 list.add(sch);
			 g.setCalendar(list);
			 entityManager.merge(g);
			 entityManager.flush();
			 return true ;
		} catch (Exception e)
		{
			System.out.println("affect plan to a gallery issue : "+e);
			return false;
		}
	}

	@Override
	public boolean EditPlanToGallery(int id, Schedule sch) 
	{
		try
		{
		 Gallery g = entityManager.find(Gallery.class, id);
		 Set<Schedule> tmp ;
		 tmp = g.getCalendar();
		 for (Schedule s : tmp) 
		 {
			    if (s.getId()==sch.getId())
			    {
			       	s= sch ;
					entityManager.merge(g);
					 return true ;
			    }
	    	}
		 return false;
	    } 
		catch (Exception e)
		{
		System.out.println("affect plan to a gallery issue : "+e);
		return false;
		}
	}

	@Override
	public boolean CancelPlanToGallery(int id, int sch)
	{
		 Gallery g = entityManager.find(Gallery.class, id);
		 Set<Schedule> tmp ;
		 tmp = g.getCalendar();
		 for (Schedule s : tmp) 
		 {
		    if (s.getId()==sch)
		    {
				tmp.remove(s);
				entityManager.merge(g);
		    	entityManager.remove(s);
				entityManager.flush();
				 return true ;
		    }
	     }
		return false;
	}

	@Override
	public void CreateGalleryOwner(GalleryOwner gOwner) {
		try 
		{	
			entityManager.persist(gOwner);
			entityManager.flush();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public GalleryOwner findOwner(int id) 
	{
		return  entityManager.find(GalleryOwner.class, id);
	}

	@Override
	public List<Gallery> FindAllGalleries() {
		return (List<Gallery>)(entityManager.createQuery("Select g from Gallery g ").getResultList());
	}

}
