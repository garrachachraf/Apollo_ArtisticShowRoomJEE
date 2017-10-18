package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Apollo.persistence.Notification;

@Local
public interface NotificationServiceLocal {

	Notification CreateNotif(Notification notif);
	void UpdateNotif(Notification notif);
	void MarkAsReaded(int id);
	List<Notification> GetAllNotifs();
	List<Notification> GetAllNotifsByUserId(int idUser);
	List<Notification> GetUnseenNotifsByUserId(int idUser);
}
