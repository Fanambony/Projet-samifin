package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.SoldeEntre;
import com.example.gestionconge.Model.Service.SoldeEntreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("solde_entre")
public class SoldeEntreController{

	private final SoldeEntreService soldeEntreService;

	public SoldeEntreController(SoldeEntreService soldeEntreService){this.soldeEntreService = soldeEntreService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<SoldeEntre> getOne(@PathVariable String id) { 
		 Optional<SoldeEntre> soldeEntre = this.soldeEntreService.getOne(id);
		return soldeEntre;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<SoldeEntre> getAll() { 
		List<SoldeEntre> listSoldeEntre = this.soldeEntreService.getAll();
		return listSoldeEntre;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody SoldeEntre formData) {
		SoldeEntre soldeEntre = new SoldeEntre();
		soldeEntreService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody SoldeEntre formData) {
		SoldeEntre soldeEntre = new SoldeEntre();
		soldeEntreService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		soldeEntreService.delete(id);
	}
}
