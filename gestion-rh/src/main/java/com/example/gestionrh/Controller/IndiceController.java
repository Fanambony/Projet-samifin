package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Indice;
import com.example.gestionrh.Model.Service.IndiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("indice")
public class IndiceController{

	private final IndiceService indiceService;

	public IndiceController(IndiceService indiceService){this.indiceService = indiceService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Indice> getOne(@PathVariable String id) { 
		 Optional<Indice> indice = this.indiceService.getOne(id);
		return indice;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Indice> getAll() { 
		List<Indice> listIndice = this.indiceService.getAll();
		return listIndice;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Indice formData) {
		Indice indice = new Indice();
		indiceService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Indice formData) {
		Indice indice = new Indice();
		indiceService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		indiceService.delete(id);
	}
}
