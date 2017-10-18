package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Apollo.persistence.Notification;

@Remote
public interface NotificationServiceRemote {
	Notification CreateNotif(Notification notif);
	void UpdateNotif(Notification notif);
	void MarkAsReaded(int id);
	List<Notification> GetAllNotifs();
	List<Notification> GetAllNotifsByUserId(int idUser);
	List<Notification> GetUnseenNotifsByUserId(int idUser);

}
