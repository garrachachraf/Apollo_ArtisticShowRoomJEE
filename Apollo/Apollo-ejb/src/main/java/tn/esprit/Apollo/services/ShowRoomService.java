package tn.esprit.Apollo.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.ShowRoom;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class ShowRoomService implements ShowRoomServiceLocal, ShowRoomServiceRemote{
	@PersistenceContext
	EntityManager em;

	@Override
	public ShowRoom createShowRoom(ShowRoom showroom) {
		em.persist(showroom);
		em.flush();
		return showroom;
	}

	@Override
	public void deleteShowroom(int id) {
		ShowRoom showRoom = em.find(ShowRoom.class, id);
		em.remove(showRoom);
	}

	@Override
	public void updateShowRoom(ShowRoom showroom) {
		em.merge(em.find(ShowRoom.class, showroom.getId()));
	}

	@Override
	public List<ShowRoom> findAll() {
	    return (em.createQuery("SELECT s FROM ShowRoom s")).getResultList();
	}

	@Override
	public ShowRoom findOne(int id) {
		return em.find(ShowRoom.class, id);
	}

	@Override
	public List<ShowRoom> findByArtist(int artistId) {
		return (em.createQuery("SELECT s FROM ShowRoom s WHERE s.artist.id = :artistId")
				.setParameter("artistId", artistId)).getResultList();
	}

	@Override
	public List<ShowRoom> findByKeyWord(String keyWord) {
		//
		List<ShowRoom> showrooms = 
				em.createQuery("SELECT s FROM ShowRoom s WHERE s.description LIKE CONCAT('%',:keyword,'%')")
				.setParameter("keyword", keyWord).getResultList();
		showrooms.addAll((List<ShowRoom>) em
				.createQuery("SELECT s FROM ShowRoom s WHERE s.title LIKE CONCAT('%',:keyword,'%')")
				.setParameter("keyword", keyWord).getResultList());
		return showrooms;
	}

	public void addArtworks(ShowRoom showroom, User user) {
		ShowRoom s = em.find(ShowRoom.class, showroom.getId());
		//List<ArtWork> myArtworks = s.getArtWorks();
		List<ArtWork> myArtworks = new ArrayList<ArtWork>();
		for (ArtWork artWork : showroom.getArtWorks()) {
			//if(! myArtworks.contains(artWork))
				myArtworks.add(em.find(ArtWork.class, artWork.getId()));
		}
		s.setTitle(showroom.getTitle());
		s.setArtWorks(myArtworks);
		em.persist(s);
	}

}
