package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.NotificationDestinataire;
import com.example.gestionconge.Model.Service.NotificationDestinataireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("notification_destinataire")
public class NotificationDestinataireController{

	private final NotificationDestinataireService notificationDestinataireService;

	public NotificationDestinataireController(NotificationDestinataireService notificationDestinataireService){this.notificationDestinataireService = notificationDestinataireService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<NotificationDestinataire> getOne(@PathVariable String id) { 
		 Optional<NotificationDestinataire> notificationDestinataire = this.notificationDestinataireService.getOne(id);
		return notificationDestinataire;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<NotificationDestinataire> getAll() { 
		List<NotificationDestinataire> listNotificationDestinataire = this.notificationDestinataireService.getAll();
		return listNotificationDestinataire;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody NotificationDestinataire formData) {
		NotificationDestinataire notificationDestinataire = new NotificationDestinataire();
		notificationDestinataireService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody NotificationDestinataire formData) {
		NotificationDestinataire notificationDestinataire = new NotificationDestinataire();
		notificationDestinataireService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		notificationDestinataireService.delete(id);
	}
}
