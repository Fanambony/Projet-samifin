package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.NombreCongeAnnee;
import com.example.gestionconge.Model.Service.NombreCongeAnneeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("nombre_conge_annee")
public class NombreCongeAnneeController{

	private final NombreCongeAnneeService nombreCongeAnneeService;

	public NombreCongeAnneeController(NombreCongeAnneeService nombreCongeAnneeService){this.nombreCongeAnneeService = nombreCongeAnneeService;}



	/* -- READ ONE -- */
	@GetMapping("/{nombre}")
	public Optional<NombreCongeAnnee> getOne(@PathVariable Double nombre) { 
		 Optional<NombreCongeAnnee> nombreCongeAnnee = this.nombreCongeAnneeService.getOne(nombre);
		return nombreCongeAnnee;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<NombreCongeAnnee> getAll() { 
		List<NombreCongeAnnee> listNombreCongeAnnee = this.nombreCongeAnneeService.getAll();
		return listNombreCongeAnnee;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody NombreCongeAnnee formData) {
		NombreCongeAnnee nombreCongeAnnee = new NombreCongeAnnee();
		nombreCongeAnneeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody NombreCongeAnnee formData) {
		NombreCongeAnnee nombreCongeAnnee = new NombreCongeAnnee();
		nombreCongeAnneeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{nombre}")
	public void delete(@PathVariable Double nombre) {
		nombreCongeAnneeService.delete(nombre);
	}
}
