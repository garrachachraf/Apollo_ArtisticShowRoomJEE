package tn.esprit.Apollo.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.ShowRoom;

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
		em.merge(showroom);
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
		return (em.createQuery("SELECT s FROM ShowRoom WHERE s.artist.id = :artistId")
				.setParameter("artistId", artistId)).getResultList();
	}

	@Override
	public List<ShowRoom> findByKeyWord(String keyWord) {
		//
		ArrayList<ShowRoom> showrooms = (ArrayList<ShowRoom>) 
				em.createQuery("SELECT s FROM ShowRoom WHERE s.description LIKE CONCAT('%',:keyword,'%')\"" )
				.setParameter("keyword", keyWord).getResultList();
		showrooms.addAll((ArrayList<ShowRoom>) em
				.createQuery("SELECT a FROM ShowRoom a WHERE s.title LIKE CONCAT('%',:keyword,'%')")
				.setParameter("keyword", keyWord));
		return showrooms;
	}

}
