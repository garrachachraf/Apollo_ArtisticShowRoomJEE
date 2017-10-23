package tn.esprit.Apollo.services;

import java.util.ArrayList;
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
		}
		catch (Exception e) 
		{
			System.out.print("find gallery by name issue : "+e);
			return null ;
		}
	}

	@Override

		try 
		{
		    for (Gallery gallery : tmpGalleries)
		    {
		    	int i =0 ;
				}
		    		galleries.add(gallery);
			}
		    return galleries ;
		}
		catch (Exception e) 
		{
			return null ;
		}
	}

	@Override
		try
		{
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
		try
		{
		 tmp = g.getCalendar();
		 for (Schedule s : tmp) 
		 {
			    if (s.getId()==sch.getId())
	    	}
		 return true ;
	   } 
		catch (Exception e)
	}

	@Override
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

	}
	@Override

	}


}
