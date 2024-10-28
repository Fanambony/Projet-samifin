package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.VUtilisateurDetailler;
import com.example.gestionconge.Model.Service.VUtilisateurDetaillerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("v_utilisateur_detailler")
public class VUtilisateurDetaillerController{

	private final VUtilisateurDetaillerService vUtilisateurDetaillerService;

	public VUtilisateurDetaillerController(VUtilisateurDetaillerService vUtilisateurDetaillerService){this.vUtilisateurDetaillerService = vUtilisateurDetaillerService;}



	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VUtilisateurDetailler> getOne(@PathVariable Long numLigne) { 
		 Optional<VUtilisateurDetailler> vUtilisateurDetailler = this.vUtilisateurDetaillerService.getOne(numLigne);
		return vUtilisateurDetailler;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VUtilisateurDetailler> getAll() { 
		List<VUtilisateurDetailler> listVUtilisateurDetailler = this.vUtilisateurDetaillerService.getAll();
		return listVUtilisateurDetailler;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody VUtilisateurDetailler formData) {
		VUtilisateurDetailler vUtilisateurDetailler = new VUtilisateurDetailler();
		vUtilisateurDetaillerService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody VUtilisateurDetailler formData) {
		VUtilisateurDetailler vUtilisateurDetailler = new VUtilisateurDetailler();
		vUtilisateurDetaillerService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{numLigne}")
	public void delete(@PathVariable Long numLigne) {
		vUtilisateurDetaillerService.delete(numLigne);
	}
}
