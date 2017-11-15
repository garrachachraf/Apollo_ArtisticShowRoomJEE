package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.persistence.WhishList;

@Local
public interface WishListServiceLocal {
	void addItem(int itemId, User user);
	void deleteItem(int itemId, User user);
	double getTotal(User user);
	WhishList getWishList(User user);
	List<WhishList> getAllWishLists();
}
