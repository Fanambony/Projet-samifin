package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.ViewSoldesUtilisateur;
import com.example.gestionconge.Model.Service.ViewSoldesUtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("view_soldes_utilisateur")
public class ViewSoldesUtilisateurController{

	private final ViewSoldesUtilisateurService viewSoldesUtilisateurService;

	public ViewSoldesUtilisateurController(ViewSoldesUtilisateurService viewSoldesUtilisateurService){this.viewSoldesUtilisateurService = viewSoldesUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{idUtilisateur}")
	public Optional<ViewSoldesUtilisateur> getOne(@PathVariable String idUtilisateur) { 
		 Optional<ViewSoldesUtilisateur> viewSoldesUtilisateur = this.viewSoldesUtilisateurService.getOne(idUtilisateur);
		return viewSoldesUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<ViewSoldesUtilisateur> getAll() { 
		List<ViewSoldesUtilisateur> listViewSoldesUtilisateur = this.viewSoldesUtilisateurService.getAll();
		return listViewSoldesUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody ViewSoldesUtilisateur formData) {
		ViewSoldesUtilisateur viewSoldesUtilisateur = new ViewSoldesUtilisateur();
		viewSoldesUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody ViewSoldesUtilisateur formData) {
		ViewSoldesUtilisateur viewSoldesUtilisateur = new ViewSoldesUtilisateur();
		viewSoldesUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{idUtilisateur}")
	public void delete(@PathVariable String idUtilisateur) {
		viewSoldesUtilisateurService.delete(idUtilisateur);
	}
}
