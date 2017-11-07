package tn.esprit.Apollo.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.ArtWork;
import tn.esprit.Apollo.persistence.Orders;
import tn.esprit.Apollo.persistence.Rating;
import tn.esprit.Apollo.persistence.User;

@Stateless
@LocalBean
public class OrderService implements OrderServiceLocal{
	@PersistenceContext
    private EntityManager em;

	@Override
	public void createOrder(List<ArtWork> artworks,User user) {
		Orders o = new Orders();
		o.setArtWorks(artworks);
		o.setOrderDate(new Date());
		o.setTotalAmount(countTotal(artworks));
		o.setUser(user);
		em.persist(o);	
	}

	private float countTotal(List<ArtWork> artworks) {
		float total =0;
		ArtWork myArtwork;
		for (ArtWork artWork : artworks) {
			myArtwork=em.find(ArtWork.class, artWork.getId());
			total+=myArtwork.getPrice();
		}
		return total;
	}

	@Override
	public List<Orders> GetAllOrders() {
		return (List<Orders>)(em.createQuery("SELECT o FROM Orders o")).getResultList();
	}

	@Override
	public List<Orders> GetOrdersByUser(int userId) {
		return (List<Orders>)(em.createQuery("SELECT o FROM Orders o WHERE  o.user.id = :userId")
				.setParameter("userId", userId)).getResultList();
	}
}
