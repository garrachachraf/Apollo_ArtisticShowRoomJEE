package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.ShowRoom;
import tn.esprit.Apollo.persistence.User;

@Remote
public interface ShowRoomServiceRemote {
	ShowRoom createShowRoom(ShowRoom showroom, User user);
	void deleteShowroom(int id);
	void updateShowRoom(ShowRoom showroom);
	List<ShowRoom> findAll();
	ShowRoom findOne(int id);
	List<ShowRoom> findByArtist(int artistId);
	List<ShowRoom> findByKeyWord(String keyWord);
}
