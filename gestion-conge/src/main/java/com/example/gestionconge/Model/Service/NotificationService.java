package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.NotificationRepository;
import com.example.gestionconge.Model.Entity.Notification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class NotificationService {

	private final NotificationRepository notificationRepository;

	public NotificationService(NotificationRepository notificationRepository) {this.notificationRepository = notificationRepository;}



	/* -- READ ONE -- */
	public Optional<Notification> getOne(Object id) { return notificationRepository.findById(id); }

	/* -- READ -- */
	public List<Notification> getAll() { return notificationRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Notification Notification) {  notificationRepository.save(Notification); }

	/* -- DELETE -- */
	public void delete(Object id) {  notificationRepository.deleteById(id); }

}
