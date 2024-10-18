package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.CorpsAppartenance;
import com.example.gestionrh.Model.Service.CorpsAppartenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("corps_appartenance")
public class CorpsAppartenanceController{

	private final CorpsAppartenanceService corpsAppartenanceService;

	public CorpsAppartenanceController(CorpsAppartenanceService corpsAppartenanceService){this.corpsAppartenanceService = corpsAppartenanceService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<CorpsAppartenance> getOne(@PathVariable String id) { 
		 Optional<CorpsAppartenance> corpsAppartenance = this.corpsAppartenanceService.getOne(id);
		return corpsAppartenance;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<CorpsAppartenance> getAll() { 
		List<CorpsAppartenance> listCorpsAppartenance = this.corpsAppartenanceService.getAll();
		return listCorpsAppartenance;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody CorpsAppartenance formData) {
		CorpsAppartenance corpsAppartenance = new CorpsAppartenance();
		corpsAppartenanceService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody CorpsAppartenance formData) {
		CorpsAppartenance corpsAppartenance = new CorpsAppartenance();
		corpsAppartenanceService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		corpsAppartenanceService.delete(id);
	}
}
