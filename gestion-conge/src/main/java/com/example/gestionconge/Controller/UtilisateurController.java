package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.Utilisateur;
import com.example.gestionconge.Model.Service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.List;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("utilisateur")
public class UtilisateurController{

	private final UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService){this.utilisateurService = utilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Utilisateur> getOne(@PathVariable String id) { 
		 Optional<Utilisateur> utilisateur = this.utilisateurService.getOne(id);
		return utilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Utilisateur> getAll() { 
		List<Utilisateur> listUtilisateur = this.utilisateurService.getAll();
		return listUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Utilisateur formData) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Utilisateur formData) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		utilisateurService.delete(id);
	}
}
