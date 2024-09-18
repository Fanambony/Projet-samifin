package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.Famille;
import com.example.gestionconge.Model.Service.FamilleService;
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
@RequestMapping("famille")
public class FamilleController{

	private final FamilleService familleService;

	public FamilleController(FamilleService familleService){this.familleService = familleService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Famille> getOne(@PathVariable String id) { 
		 Optional<Famille> famille = this.familleService.getOne(id);
		return famille;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Famille> getAll() { 
		List<Famille> listFamille = this.familleService.getAll();
		return listFamille;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Famille formData) {
		Famille famille = new Famille();
		familleService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Famille formData) {
		Famille famille = new Famille();
		familleService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		familleService.delete(id);
	}
}
