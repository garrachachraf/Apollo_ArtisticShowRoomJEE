package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Apollo.persistence.Notification;

@Stateless
@LocalBean
public class NotificationService implements NotificationServiceLocal , NotificationServiceRemote{
	@PersistenceContext
	EntityManager em;

	@Override
	public Notification CreateNotif(Notification notif) {
		// TODO Auto-generated method stub
		em.persist(notif);
		em.flush();
		return notif;
	}

	@Override
	public void UpdateNotif(Notification notif) {
		// TODO Auto-generated method stub
		em.merge(notif);
	}

	@Override
	public void MarkAsReaded(int id) {
		// TODO Auto-generated method stub
		Notification n = em.find(Notification.class, id);
		n.setSeen(true);
		em.merge(n);
	}

	@Override
	public List<Notification> GetAllNotifs() {
		// TODO Auto-generated method stub
		return (em.createQuery("SELECT n FROM Notification n")).getResultList();
	}

	@Override
	public List<Notification> GetAllNotifsByUserId(int idUser) {
		// TODO Auto-generated method stub
		return (em.createQuery("SELECT n FROM Notification n where n.user.id = :userId").setParameter("userId", idUser)).getResultList();
	}

	@Override
	public List<Notification> GetUnseenNotifsByUserId(int idUser) {
		// TODO Auto-generated method stub
		return (em.createQuery("SELECT n FROM Notification n where n.user.id = :userId AND n.seen = false").setParameter("userId", idUser)).getResultList();
	}


}
