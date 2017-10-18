package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Apollo.persistence.ShowRoom;

@Local
public interface ShowRoomServiceLocal {

	ShowRoom createShowRoom(ShowRoom showroom);
	void deleteShowroom(int id);
	void updateShowRoom(ShowRoom showroom);
	List<ShowRoom> findAll();
	ShowRoom findOne(int id);
	List<ShowRoom> findByArtist(int artistId);
	List<ShowRoom> findByKeyWord(String keyWord);
}
