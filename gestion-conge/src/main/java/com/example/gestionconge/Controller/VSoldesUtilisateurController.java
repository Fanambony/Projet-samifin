package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.VSoldesUtilisateur;
import com.example.gestionconge.Model.Service.VSoldesUtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("v_soldes_utilisateur")
public class VSoldesUtilisateurController{

	private final VSoldesUtilisateurService vSoldesUtilisateurService;

	public VSoldesUtilisateurController(VSoldesUtilisateurService vSoldesUtilisateurService){this.vSoldesUtilisateurService = vSoldesUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VSoldesUtilisateur> getOne(@PathVariable Long numLigne) { 
		 Optional<VSoldesUtilisateur> vSoldesUtilisateur = this.vSoldesUtilisateurService.getOne(numLigne);
		return vSoldesUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VSoldesUtilisateur> getAll() { 
		List<VSoldesUtilisateur> listVSoldesUtilisateur = this.vSoldesUtilisateurService.getAll();
		return listVSoldesUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody VSoldesUtilisateur formData) {
		VSoldesUtilisateur vSoldesUtilisateur = new VSoldesUtilisateur();
		vSoldesUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody VSoldesUtilisateur formData) {
		VSoldesUtilisateur vSoldesUtilisateur = new VSoldesUtilisateur();
		vSoldesUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{numLigne}")
	public void delete(@PathVariable Long numLigne) {
		vSoldesUtilisateurService.delete(numLigne);
	}
}
