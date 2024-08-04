package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Service.FonctionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("fonction")
public class FonctionController{

	private final FonctionService fonctionService;

	public FonctionController(FonctionService fonctionService){this.fonctionService = fonctionService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Fonction> getOne(@PathVariable String id) { 
		 Optional<Fonction> fonction = this.fonctionService.getOne(id);
		return fonction;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Fonction> getAll() { 
		List<Fonction> listFonction = this.fonctionService.getAll();
		return listFonction;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Fonction formData) {
		Fonction fonction = new Fonction();
		fonctionService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Fonction formData) {
		Fonction fonction = new Fonction();
		fonctionService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		fonctionService.delete(id);
	}
}
