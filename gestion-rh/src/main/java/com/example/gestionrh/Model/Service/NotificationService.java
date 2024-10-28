package com.example.gestionrh.Model.Service;

import com.example.gestionrh.Context.NotificationRepository;
import com.example.gestionrh.Model.Entity.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	/* -- READ ONE -- */
	public Optional<Notification> getOne(Object id) { return notificationRepository.findById(id); }

	/* -- READ -- */
	public List<Notification> getAll() { return notificationRepository.findAll(); }

	/* -- CREATE ET UPDATE -- */
	public void create(Notification Notification) {  notificationRepository.save(Notification); }

	/* -- DELETE -- */
	public void delete(Object id) {  notificationRepository.deleteById(id); }

}
