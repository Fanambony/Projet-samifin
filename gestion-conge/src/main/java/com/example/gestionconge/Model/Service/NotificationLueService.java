package com.example.gestionconge.Model.Service;

import com.example.gestionconge.Context.NotificationLueRepository;
import com.example.gestionconge.Model.Entity.NotificationLue;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class NotificationLueService {

	private final NotificationLueRepository notificationLueRepository;

	public NotificationLueService(NotificationLueRepository notificationLueRepository) {this.notificationLueRepository = notificationLueRepository;}



	/* -- READ ONE -- */
	public Optional<NotificationLue> getOne(Object id) { return notificationLueRepository.findById(id); }

	/* -- READ -- */
	public List<NotificationLue> getAll() { return notificationLueRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(NotificationLue NotificationLue) {  notificationLueRepository.save(NotificationLue); }

	/* -- DELETE -- */
	public void delete(Object id) {  notificationLueRepository.deleteById(id); }

}
