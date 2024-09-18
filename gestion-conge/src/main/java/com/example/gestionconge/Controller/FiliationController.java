package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.Filiation;
import com.example.gestionconge.Model.Service.FiliationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("filiation")
public class FiliationController{

	private final FiliationService filiationService;

	public FiliationController(FiliationService filiationService){this.filiationService = filiationService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Filiation> getOne(@PathVariable String id) { 
		 Optional<Filiation> filiation = this.filiationService.getOne(id);
		return filiation;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Filiation> getAll() { 
		List<Filiation> listFiliation = this.filiationService.getAll();
		return listFiliation;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Filiation formData) {
		Filiation filiation = new Filiation();
		filiationService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Filiation formData) {
		Filiation filiation = new Filiation();
		filiationService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		filiationService.delete(id);
	}
}
