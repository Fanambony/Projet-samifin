package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.NotificationDestinataireRepository;
import com.example.gestionconge.Model.Entity.NotificationDestinataire;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class NotificationDestinataireService {

	private final NotificationDestinataireRepository notificationDestinataireRepository;

	public NotificationDestinataireService(NotificationDestinataireRepository notificationDestinataireRepository) {this.notificationDestinataireRepository = notificationDestinataireRepository;}



	/* -- READ ONE -- */
	public Optional<NotificationDestinataire> getOne(Object id) { return notificationDestinataireRepository.findById(id); }

	/* -- READ -- */
	public List<NotificationDestinataire> getAll() { return notificationDestinataireRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(NotificationDestinataire NotificationDestinataire) {  notificationDestinataireRepository.save(NotificationDestinataire); }

	/* -- DELETE -- */
	public void delete(Object id) {  notificationDestinataireRepository.deleteById(id); }

}
