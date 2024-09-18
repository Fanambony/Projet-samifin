package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.VTotalCongeUtilisateur;
import com.example.gestionconge.Model.Service.VTotalCongeUtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("v_total_conge_utilisateur")
public class VTotalCongeUtilisateurController{

	private final VTotalCongeUtilisateurService vTotalCongeUtilisateurService;

	public VTotalCongeUtilisateurController(VTotalCongeUtilisateurService vTotalCongeUtilisateurService){this.vTotalCongeUtilisateurService = vTotalCongeUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VTotalCongeUtilisateur> getOne(@PathVariable Long numLigne) { 
		 Optional<VTotalCongeUtilisateur> vTotalCongeUtilisateur = this.vTotalCongeUtilisateurService.getOne(numLigne);
		return vTotalCongeUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VTotalCongeUtilisateur> getAll() { 
		List<VTotalCongeUtilisateur> listVTotalCongeUtilisateur = this.vTotalCongeUtilisateurService.getAll();
		return listVTotalCongeUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody VTotalCongeUtilisateur formData) {
		VTotalCongeUtilisateur vTotalCongeUtilisateur = new VTotalCongeUtilisateur();
		vTotalCongeUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody VTotalCongeUtilisateur formData) {
		VTotalCongeUtilisateur vTotalCongeUtilisateur = new VTotalCongeUtilisateur();
		vTotalCongeUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{numLigne}")
	public void delete(@PathVariable Long numLigne) {
		vTotalCongeUtilisateurService.delete(numLigne);
	}
}
