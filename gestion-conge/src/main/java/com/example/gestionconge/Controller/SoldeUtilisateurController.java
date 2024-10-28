package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.SoldeUtilisateur;
import com.example.gestionconge.Model.Service.SoldeUtilisateurService;
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
@RequestMapping("solde_utilisateur")
public class SoldeUtilisateurController{

	private final SoldeUtilisateurService soldeUtilisateurService;

	public SoldeUtilisateurController(SoldeUtilisateurService soldeUtilisateurService){this.soldeUtilisateurService = soldeUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<SoldeUtilisateur> getOne(@PathVariable String id) { 
		 Optional<SoldeUtilisateur> soldeUtilisateur = this.soldeUtilisateurService.getOne(id);
		return soldeUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<SoldeUtilisateur> getAll() { 
		List<SoldeUtilisateur> listSoldeUtilisateur = this.soldeUtilisateurService.getAll();
		return listSoldeUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody SoldeUtilisateur formData) {
		SoldeUtilisateur soldeUtilisateur = new SoldeUtilisateur();
		soldeUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody SoldeUtilisateur formData) {
		SoldeUtilisateur soldeUtilisateur = new SoldeUtilisateur();
		soldeUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		soldeUtilisateurService.delete(id);
	}
}
