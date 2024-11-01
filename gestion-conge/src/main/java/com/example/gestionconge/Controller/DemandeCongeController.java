package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.DemandeConge;
import com.example.gestionconge.Model.Service.DemandeCongeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import java.sql.Date;
import java.util.List;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("demande_conge")
public class DemandeCongeController{

	private final DemandeCongeService demandeCongeService;

	public DemandeCongeController(DemandeCongeService demandeCongeService){this.demandeCongeService = demandeCongeService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<DemandeConge> getOne(@PathVariable String id) { 
		 Optional<DemandeConge> demandeConge = this.demandeCongeService.getOne(id);
		return demandeConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<DemandeConge> getAll() { 
		List<DemandeConge> listDemandeConge = this.demandeCongeService.getAll();
		return listDemandeConge;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody DemandeConge formData) {
		DemandeConge demandeConge = new DemandeConge();
		demandeCongeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody DemandeConge formData) {
		DemandeConge demandeConge = new DemandeConge();
		demandeCongeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		demandeCongeService.delete(id);
	}
}
