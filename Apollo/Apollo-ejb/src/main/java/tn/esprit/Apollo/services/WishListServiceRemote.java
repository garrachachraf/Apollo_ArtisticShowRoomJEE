package tn.esprit.Apollo.services;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.WhishList;

@Remote
public interface WishListServiceRemote {
	void addItem(int itemId);
	void deleteItem(int itemId);
	double getTotal();
	WhishList getWishList();
}
