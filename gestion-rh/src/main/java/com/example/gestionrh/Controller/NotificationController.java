package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Notification;
import com.example.gestionrh.Model.Entity.NotificationDestinataire;
import com.example.gestionrh.Model.Entity.VEtatDemande;
import com.example.gestionrh.Model.Service.NotificationDestinataireService;
import com.example.gestionrh.Model.Service.NotificationService;
import com.example.gestionrh.Model.Service.VEtatDemandeService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.sql.Timestamp;

@Controller
@RequestMapping("notification")
public class NotificationController{

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private VEtatDemandeService vEtatDemandeService;

	@Autowired
	private NotificationDestinataireService notificationDestinataireService;

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

	// @GetMapping("/detail-demande")
	// public String detailDemande(@RequestParam("idDemande") String idDemande, 
	// 							HttpServletRequest request,
	// 							@RequestParam("idNotificationDestinataire") String idNotificationDestinataire
	// 							) {
	// 	VEtatDemande detailDemande = vEtatDemandeService.getByDemande(idDemande);
	// 	Optional<NotificationDestinataire> notificationDestinataireOptional = notificationDestinataireService.getOne(idNotificationDestinataire);
	// 	NotificationDestinataire notificationDestinataire = notificationDestinataireOptional.get();

	// 	notificationDestinataire.setLu(true);
	// 	notificationDestinataire.setDateLu(null);

	// 	request.setAttribute("demande", detailDemande);
	// 	return "/notification/detail-notification";
	// }

	@GetMapping("/detail-demande")
	public String detailDemande(@RequestParam("idDemande") String idDemande, 
								HttpServletRequest request,
								@RequestParam("idNotificationDestinataire") String idNotificationDestinataire) {
		VEtatDemande detailDemande = vEtatDemandeService.getByDemande(idDemande);
		Optional<NotificationDestinataire> notificationDestinataireOptional = notificationDestinataireService.getOne(idNotificationDestinataire);
		
		if (notificationDestinataireOptional.isPresent()) {
			NotificationDestinataire notificationDestinataire = notificationDestinataireOptional.get();
			notificationDestinataire.setLu(true);
			
			Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        	notificationDestinataire.setDateLu(currentTimestamp);

			// Sauvegarder la mise à jour si nécessaire
			notificationDestinataireService.create(notificationDestinataire);
		}

		request.setAttribute("demande", detailDemande);
		return "/notification/detail-notification";
	}	
}