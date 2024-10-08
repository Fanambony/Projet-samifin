package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.TypeUtilisateur;
import com.example.gestionconge.Model.Service.TypeUtilisateurService;
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
@RequestMapping("type_utilisateur")
public class TypeUtilisateurController{

	private final TypeUtilisateurService typeUtilisateurService;

	public TypeUtilisateurController(TypeUtilisateurService typeUtilisateurService){this.typeUtilisateurService = typeUtilisateurService;}



	/* -- READ ONE -- */
	@GetMapping("/{libelle}")
	public Optional<TypeUtilisateur> getOne(@PathVariable String libelle) { 
		 Optional<TypeUtilisateur> typeUtilisateur = this.typeUtilisateurService.getOne(libelle);
		return typeUtilisateur;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<TypeUtilisateur> getAll() { 
		List<TypeUtilisateur> listTypeUtilisateur = this.typeUtilisateurService.getAll();
		return listTypeUtilisateur;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody TypeUtilisateur formData) {
		TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
		typeUtilisateurService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody TypeUtilisateur formData) {
		TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
		typeUtilisateurService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{libelle}")
	public void delete(@PathVariable String libelle) {
		typeUtilisateurService.delete(libelle);
	}
}
