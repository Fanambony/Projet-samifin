package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Filiation;
import com.example.gestionrh.Model.Service.FiliationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("filiation")
public class FiliationController{

	@Autowired
	private FiliationService filiationService;

	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Filiation> getOne(@PathVariable String id) { 
		 Optional<Filiation> filiation = this.filiationService.getOne(id);
		return filiation;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Filiation> getAll() { 
		List<Filiation> listFiliation = this.filiationService.getAll();
		return listFiliation;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Filiation formData) {
		Filiation filiation = new Filiation();
		filiationService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Filiation formData) {
		Filiation filiation = new Filiation();
		filiationService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		filiationService.delete(id);
	}
}
