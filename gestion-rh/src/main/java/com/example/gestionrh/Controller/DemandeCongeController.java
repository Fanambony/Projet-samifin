package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.DemandeConge;
import com.example.gestionrh.Model.Entity.TypeConge;
import com.example.gestionrh.Model.Service.DemandeCongeService;
import com.example.gestionrh.Model.Service.TypeCongeService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("demande_conge")
public class DemandeCongeController{

	private final DemandeCongeService demandeCongeService;
	private final TypeCongeService typeCongeService;

	public DemandeCongeController(DemandeCongeService demandeCongeService, TypeCongeService typeCongeService){
		this.demandeCongeService = demandeCongeService;
		this.typeCongeService = typeCongeService;
	}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<DemandeConge> getOne(@PathVariable String id) { 
		 Optional<DemandeConge> demandeConge = this.demandeCongeService.getOne(id);
		return demandeConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<DemandeConge> getAll() { 
		List<DemandeConge> listDemandeConge = this.demandeCongeService.getAll();
		return listDemandeConge;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody DemandeConge formData) {
		DemandeConge demandeConge = new DemandeConge();
		demandeCongeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody DemandeConge formData) {
		DemandeConge demandeConge = new DemandeConge();
		demandeCongeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		demandeCongeService.delete(id);
	}

	@GetMapping("/demande-conge")
	public String demandeConge(HttpServletRequest request) {
		List<TypeConge> typeConge = typeCongeService.getAll();
		request.setAttribute("typeConge", typeConge);
		return "conge/demande-conge";
	}
	
}
