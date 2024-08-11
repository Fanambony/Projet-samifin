package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.TypeConge;
import com.example.gestionconge.Model.Service.TypeCongeService;
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
@RequestMapping("type_conge")
public class TypeCongeController{

	private final TypeCongeService typeCongeService;

	public TypeCongeController(TypeCongeService typeCongeService){this.typeCongeService = typeCongeService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<TypeConge> getOne(@PathVariable String id) { 
		 Optional<TypeConge> typeConge = this.typeCongeService.getOne(id);
		return typeConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<TypeConge> getAll() { 
		List<TypeConge> listTypeConge = this.typeCongeService.getAll();
		return listTypeConge;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody TypeConge formData) {
		TypeConge typeConge = new TypeConge();
		typeCongeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody TypeConge formData) {
		TypeConge typeConge = new TypeConge();
		typeCongeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		typeCongeService.delete(id);
	}
}
