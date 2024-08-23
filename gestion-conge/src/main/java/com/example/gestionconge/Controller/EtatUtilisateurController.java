package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.EtatUtilisateur;
import com.example.gestionconge.Model.Service.EtatUtilisateurService;
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
@RequestMapping("etat_utilisateur")
public class EtatUtilisateurController{

	private final EtatUtilisateurService etatUtilisateurService;

	public EtatUtilisateurController(EtatUtilisateurService etatUtilisateurService){this.etatUtilisateurService = etatUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{libelle}")
	public Optional<EtatUtilisateur> getOne(@PathVariable String libelle) { 
		 Optional<EtatUtilisateur> etatUtilisateur = this.etatUtilisateurService.getOne(libelle);
		return etatUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<EtatUtilisateur> getAll() { 
		List<EtatUtilisateur> listEtatUtilisateur = this.etatUtilisateurService.getAll();
		return listEtatUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody EtatUtilisateur formData) {
		EtatUtilisateur etatUtilisateur = new EtatUtilisateur();
		etatUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody EtatUtilisateur formData) {
		EtatUtilisateur etatUtilisateur = new EtatUtilisateur();
		etatUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{libelle}")
	public void delete(@PathVariable String libelle) {
		etatUtilisateurService.delete(libelle);
	}
}
