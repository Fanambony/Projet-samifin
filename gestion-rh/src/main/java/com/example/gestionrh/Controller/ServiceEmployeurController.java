package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.ServiceEmployeur;
import com.example.gestionrh.Model.Service.ServiceEmployeurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("service_employeur")
public class ServiceEmployeurController{

	private final ServiceEmployeurService serviceEmployeurService;

	public ServiceEmployeurController(ServiceEmployeurService serviceEmployeurService){this.serviceEmployeurService = serviceEmployeurService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<ServiceEmployeur> getOne(@PathVariable String id) { 
		 Optional<ServiceEmployeur> serviceEmployeur = this.serviceEmployeurService.getOne(id);
		return serviceEmployeur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<ServiceEmployeur> getAll() { 
		List<ServiceEmployeur> listServiceEmployeur = this.serviceEmployeurService.getAll();
		return listServiceEmployeur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody ServiceEmployeur formData) {
		ServiceEmployeur serviceEmployeur = new ServiceEmployeur();
		serviceEmployeurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody ServiceEmployeur formData) {
		ServiceEmployeur serviceEmployeur = new ServiceEmployeur();
		serviceEmployeurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		serviceEmployeurService.delete(id);
	}
}
