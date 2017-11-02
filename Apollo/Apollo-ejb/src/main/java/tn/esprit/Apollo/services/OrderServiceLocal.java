package tn.esprit.Apollo.services;

import java.util.List;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.Orders;
import tn.esprit.Apollo.persistence.User;

public interface OrderServiceLocal {
	void createOrder(List<ArtWork> Artworks,User user);
	List<Orders> GetAllOrders();
	List<Orders> GetOrdersByUser(int userId);
}
