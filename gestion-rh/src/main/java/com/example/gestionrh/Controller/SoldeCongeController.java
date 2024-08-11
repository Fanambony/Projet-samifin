package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.SoldeConge;
import com.example.gestionrh.Model.Service.SoldeCongeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("solde_conge")
public class SoldeCongeController{

	private final SoldeCongeService soldeCongeService;

	public SoldeCongeController(SoldeCongeService soldeCongeService){this.soldeCongeService = soldeCongeService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<SoldeConge> getOne(@PathVariable String id) { 
		 Optional<SoldeConge> soldeConge = this.soldeCongeService.getOne(id);
		return soldeConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<SoldeConge> getAll() { 
		List<SoldeConge> listSoldeConge = this.soldeCongeService.getAll();
		return listSoldeConge;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody SoldeConge formData) {
		SoldeConge soldeConge = new SoldeConge();
		soldeCongeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody SoldeConge formData) {
		SoldeConge soldeConge = new SoldeConge();
		soldeCongeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		soldeCongeService.delete(id);
	}
}
