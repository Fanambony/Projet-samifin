package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Qualite;
import com.example.gestionrh.Model.Service.QualiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("qualite")
public class QualiteController{

	private final QualiteService qualiteService;

	public QualiteController(QualiteService qualiteService){this.qualiteService = qualiteService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<Qualite> getOne(@PathVariable String id) { 
		 Optional<Qualite> qualite = this.qualiteService.getOne(id);
		return qualite;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<Qualite> getAll() { 
		List<Qualite> listQualite = this.qualiteService.getAll();
		return listQualite;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody Qualite formData) {
		Qualite qualite = new Qualite();
		qualiteService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody Qualite formData) {
		Qualite qualite = new Qualite();
		qualiteService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		qualiteService.delete(id);
	}
}
