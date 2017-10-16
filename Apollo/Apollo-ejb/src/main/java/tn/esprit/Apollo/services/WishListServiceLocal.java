package tn.esprit.Apollo.services;

import javax.ejb.Local;

import tn.esprit.Apollo.persistence.WhishList;

@Local
public interface WishListServiceLocal {
	void addItem(int itemId);
	void deleteItem(int itemId);
	double getTotal();
	WhishList getWishList();
}
