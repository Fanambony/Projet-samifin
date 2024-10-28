package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.NotificationDestinataire;
import com.example.gestionrh.Model.Service.NotificationDestinataireService;
import com.example.gestionrh.utils.PaginationConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("notification_destinataire")
public class NotificationDestinataireController{

	@Autowired
	private NotificationDestinataireService notificationDestinataireService;

	@Autowired
    private PaginationConfig paginationConfig;

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

	@GetMapping("/count-notification-non-lu")
    @ResponseBody // Indique que la réponse sera directement envoyée dans le corps de la réponse
    public int getNotificationsNonLu(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        return notificationDestinataireService.getCountNotificationNonLu(userId);
    }

	@GetMapping("/notifications")
	@ResponseBody
	public List<NotificationDestinataire> getNotifications(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		List<NotificationDestinataire> notifications = notificationDestinataireService.getNotificationsByUserNonLu(userId);
		
		return notifications;
	}

	@GetMapping("/tous_notifications")
	public String getTousNotifications(HttpSession session, 
										HttpServletRequest request,
										@RequestParam(defaultValue = "0") int page,
										@RequestParam(required = false) Integer size
										) {
								
		int paginationSize = (size != null) ? size : paginationConfig.getDefaultPaginationSize();
		Pageable pageable = PageRequest.of(page, paginationSize);
										
		String userId = (String) session.getAttribute("userId");
		Page<NotificationDestinataire> notifications = notificationDestinataireService.getNotificationsByUser(userId, pageable);

		request.setAttribute("notifications", notifications);		
		return "/notification/list-notification";
	}
}