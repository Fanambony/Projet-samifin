package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.SoldeFinAnnee;
import com.example.gestionconge.Model.Service.SoldeFinAnneeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("solde_fin_annee")
public class SoldeFinAnneeController{

	private final SoldeFinAnneeService soldeFinAnneeService;

	public SoldeFinAnneeController(SoldeFinAnneeService soldeFinAnneeService){this.soldeFinAnneeService = soldeFinAnneeService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<SoldeFinAnnee> getOne(@PathVariable String id) { 
		 Optional<SoldeFinAnnee> soldeFinAnnee = this.soldeFinAnneeService.getOne(id);
		return soldeFinAnnee;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<SoldeFinAnnee> getAll() { 
		List<SoldeFinAnnee> listSoldeFinAnnee = this.soldeFinAnneeService.getAll();
		return listSoldeFinAnnee;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody SoldeFinAnnee formData) {
		SoldeFinAnnee soldeFinAnnee = new SoldeFinAnnee();
		soldeFinAnneeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody SoldeFinAnnee formData) {
		SoldeFinAnnee soldeFinAnnee = new SoldeFinAnnee();
		soldeFinAnneeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		soldeFinAnneeService.delete(id);
	}
}
