package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.Notification;
import com.example.gestionconge.Model.Service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("notification")
public class NotificationController{

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService){this.notificationService = notificationService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Notification> getOne(@PathVariable String id) { 
		 Optional<Notification> notification = this.notificationService.getOne(id);
		return notification;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Notification> getAll() { 
		List<Notification> listNotification = this.notificationService.getAll();
		return listNotification;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Notification formData) {
		Notification notification = new Notification();
		notificationService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Notification formData) {
		Notification notification = new Notification();
		notificationService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		notificationService.delete(id);
	}
}
