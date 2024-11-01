package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.VInterimValide;
import com.example.gestionconge.Model.Service.VInterimValideService;
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
@RequestMapping("v_interim_valide")
public class VInterimValideController{

	private final VInterimValideService vInterimValideService;

	public VInterimValideController(VInterimValideService vInterimValideService){this.vInterimValideService = vInterimValideService;}



	/* -- READ ONE -- */
	@GetMapping("/{idInterimUtilisateur}")
	public Optional<VInterimValide> getOne(@PathVariable String idInterimUtilisateur) { 
		 Optional<VInterimValide> vInterimValide = this.vInterimValideService.getOne(idInterimUtilisateur);
		return vInterimValide;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VInterimValide> getAll() { 
		List<VInterimValide> listVInterimValide = this.vInterimValideService.getAll();
		return listVInterimValide;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody VInterimValide formData) {
		VInterimValide vInterimValide = new VInterimValide();
		vInterimValideService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody VInterimValide formData) {
		VInterimValide vInterimValide = new VInterimValide();
		vInterimValideService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{idInterimUtilisateur}")
	public void delete(@PathVariable String idInterimUtilisateur) {
		vInterimValideService.delete(idInterimUtilisateur);
	}
}
