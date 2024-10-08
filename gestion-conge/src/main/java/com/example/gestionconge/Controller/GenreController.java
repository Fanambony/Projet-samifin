package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.Genre;
import com.example.gestionconge.Model.Service.GenreService;
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
@RequestMapping("genre")
public class GenreController{

	private final GenreService genreService;

	public GenreController(GenreService genreService){this.genreService = genreService;}



	/* -- READ ONE -- */
	@GetMapping("/{libelle}")
	public Optional<Genre> getOne(@PathVariable String libelle) { 
		 Optional<Genre> genre = this.genreService.getOne(libelle);
		return genre;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Genre> getAll() { 
		List<Genre> listGenre = this.genreService.getAll();
		return listGenre;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Genre formData) {
		Genre genre = new Genre();
		genreService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Genre formData) {
		Genre genre = new Genre();
		genreService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{libelle}")
	public void delete(@PathVariable String libelle) {
		genreService.delete(libelle);
	}
}
