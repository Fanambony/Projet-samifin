package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.NotificationDestinataireRepository;
import com.example.gestionrh.Model.Entity.NotificationDestinataire;
import com.example.gestionrh.Model.Entity.VUtilisateurDetailler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class NotificationDestinataireService {

	@Autowired
	private NotificationDestinataireRepository notificationDestinataireRepository;

	/* -- READ ONE -- */
	public Optional<NotificationDestinataire> getOne(Object id) { return notificationDestinataireRepository.findById(id); }

	/* -- READ -- */
	public List<NotificationDestinataire> getAll() { return notificationDestinataireRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(NotificationDestinataire NotificationDestinataire) {  notificationDestinataireRepository.save(NotificationDestinataire); }

	/* -- DELETE -- */
	public void delete(Object id) {  notificationDestinataireRepository.deleteById(id); }

	
	public void envoyerNotifications(String idNotification, List<VUtilisateurDetailler> listDestinataires, Boolean lue) {
		for (VUtilisateurDetailler destinataire : listDestinataires) {
			NotificationDestinataire notificationDestinataire = new NotificationDestinataire(idNotification, destinataire.getIdUtilisateur(), lue);
			this.create(notificationDestinataire);
		}
	}

	public void validerRefusNotifications(String idNotification, String idDestinataires, Boolean lue) {
		NotificationDestinataire notificationDestinataire = new NotificationDestinataire(idNotification, idDestinataires, lue);
		this.create(notificationDestinataire);
	}

	// compter nbr notif
	public int getCountNotificationNonLu(String userId) {
        int count = notificationDestinataireRepository.countByDestinataireAndLuFalse(userId);
		return count;
    }

	// notif par utilisateur
	public Page<NotificationDestinataire> getNotificationsByUser(String userId, Pageable pageable) {
		return notificationDestinataireRepository.findByDestinataireOrderByDateCreationDesc(userId, pageable);
	}

	public List<NotificationDestinataire> getNotificationsByUserNonLu(String userId) {
		return notificationDestinataireRepository.findByDestinataireOrderByDateCreationDescAndNonLu(userId);
	}
	
}
