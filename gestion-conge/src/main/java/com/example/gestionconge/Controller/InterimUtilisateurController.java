package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.InterimUtilisateur;
import com.example.gestionconge.Model.Service.InterimUtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("interim_utilisateur")
public class InterimUtilisateurController{

	private final InterimUtilisateurService interimUtilisateurService;

	public InterimUtilisateurController(InterimUtilisateurService interimUtilisateurService){this.interimUtilisateurService = interimUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<InterimUtilisateur> getOne(@PathVariable String id) { 
		 Optional<InterimUtilisateur> interimUtilisateur = this.interimUtilisateurService.getOne(id);
		return interimUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<InterimUtilisateur> getAll() { 
		List<InterimUtilisateur> listInterimUtilisateur = this.interimUtilisateurService.getAll();
		return listInterimUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody InterimUtilisateur formData) {
		InterimUtilisateur interimUtilisateur = new InterimUtilisateur();
		interimUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody InterimUtilisateur formData) {
		InterimUtilisateur interimUtilisateur = new InterimUtilisateur();
		interimUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		interimUtilisateurService.delete(id);
	}
}
