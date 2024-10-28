package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.NotificationLue;
import com.example.gestionconge.Model.Service.NotificationLueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("notification_lue")
public class NotificationLueController{

	private final NotificationLueService notificationLueService;

	public NotificationLueController(NotificationLueService notificationLueService){this.notificationLueService = notificationLueService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<NotificationLue> getOne(@PathVariable String id) { 
		 Optional<NotificationLue> notificationLue = this.notificationLueService.getOne(id);
		return notificationLue;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<NotificationLue> getAll() { 
		List<NotificationLue> listNotificationLue = this.notificationLueService.getAll();
		return listNotificationLue;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody NotificationLue formData) {
		NotificationLue notificationLue = new NotificationLue();
		notificationLueService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody NotificationLue formData) {
		NotificationLue notificationLue = new NotificationLue();
		notificationLueService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		notificationLueService.delete(id);
	}
}
