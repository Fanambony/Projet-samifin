package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.JourFerie;
import com.example.gestionconge.Model.Service.JourFerieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("jour_ferie")
public class JourFerieController{

	private final JourFerieService jourFerieService;

	public JourFerieController(JourFerieService jourFerieService){this.jourFerieService = jourFerieService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<JourFerie> getOne(@PathVariable String id) { 
		 Optional<JourFerie> jourFerie = this.jourFerieService.getOne(id);
		return jourFerie;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<JourFerie> getAll() { 
		List<JourFerie> listJourFerie = this.jourFerieService.getAll();
		return listJourFerie;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody JourFerie formData) {
		JourFerie jourFerie = new JourFerie();
		jourFerieService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody JourFerie formData) {
		JourFerie jourFerie = new JourFerie();
		jourFerieService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		jourFerieService.delete(id);
	}
}
