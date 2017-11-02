package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.persistence.WhishList;

@Remote
public interface WishListServiceRemote {
	void addItem(int itemId, User user);
	void deleteItem(int itemId, User user);
	double getTotal(User user);
	WhishList getWishList(User user);
	List<WhishList> getAllWishLists();
}
