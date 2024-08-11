package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.DetailUtilisateur;
import com.example.gestionconge.Model.Service.DetailUtilisateurService;
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
@RequestMapping("detail_utilisateur")
public class DetailUtilisateurController{

	private final DetailUtilisateurService detailUtilisateurService;

	public DetailUtilisateurController(DetailUtilisateurService detailUtilisateurService){this.detailUtilisateurService = detailUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<DetailUtilisateur> getOne(@PathVariable String id) { 
		 Optional<DetailUtilisateur> detailUtilisateur = this.detailUtilisateurService.getOne(id);
		return detailUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<DetailUtilisateur> getAll() { 
		List<DetailUtilisateur> listDetailUtilisateur = this.detailUtilisateurService.getAll();
		return listDetailUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody DetailUtilisateur formData) {
		DetailUtilisateur detailUtilisateur = new DetailUtilisateur();
		detailUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody DetailUtilisateur formData) {
		DetailUtilisateur detailUtilisateur = new DetailUtilisateur();
		detailUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		detailUtilisateurService.delete(id);
	}
}
