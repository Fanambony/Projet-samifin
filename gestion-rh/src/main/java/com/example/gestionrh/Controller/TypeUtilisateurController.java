package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.TypeUtilisateur;
import com.example.gestionrh.Model.Service.TypeUtilisateurService;
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
	@GetMapping("/{id}")
	public Optional<TypeUtilisateur> getOne(@PathVariable String id) { 
		 Optional<TypeUtilisateur> typeUtilisateur = this.typeUtilisateurService.getOne(id);
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
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		typeUtilisateurService.delete(id);
	}
}
