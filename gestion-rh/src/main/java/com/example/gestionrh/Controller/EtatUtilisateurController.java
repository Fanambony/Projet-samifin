package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.EtatUtilisateur;
import com.example.gestionrh.Model.Service.EtatUtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private EtatUtilisateurService etatUtilisateurService;

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
