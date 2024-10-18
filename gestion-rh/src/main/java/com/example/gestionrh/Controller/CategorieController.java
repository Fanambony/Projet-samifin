package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Categorie;
import com.example.gestionrh.Model.Service.CategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("categorie")
public class CategorieController{

	private final CategorieService categorieService;

	public CategorieController(CategorieService categorieService){this.categorieService = categorieService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Categorie> getOne(@PathVariable String id) { 
		 Optional<Categorie> categorie = this.categorieService.getOne(id);
		return categorie;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Categorie> getAll() { 
		List<Categorie> listCategorie = this.categorieService.getAll();
		return listCategorie;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Categorie formData) {
		Categorie categorie = new Categorie();
		categorieService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Categorie formData) {
		Categorie categorie = new Categorie();
		categorieService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		categorieService.delete(id);
	}
}
