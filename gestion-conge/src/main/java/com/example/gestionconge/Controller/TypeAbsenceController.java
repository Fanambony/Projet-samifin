package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.TypeAbsence;
import com.example.gestionconge.Model.Service.TypeAbsenceService;
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
@RequestMapping("type_absence")
public class TypeAbsenceController{

	private final TypeAbsenceService typeAbsenceService;

	public TypeAbsenceController(TypeAbsenceService typeAbsenceService){this.typeAbsenceService = typeAbsenceService;}



	/* -- READ ONE -- */
	@GetMapping("/{libelle}")
	public Optional<TypeAbsence> getOne(@PathVariable String libelle) { 
		 Optional<TypeAbsence> typeAbsence = this.typeAbsenceService.getOne(libelle);
		return typeAbsence;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<TypeAbsence> getAll() { 
		List<TypeAbsence> listTypeAbsence = this.typeAbsenceService.getAll();
		return listTypeAbsence;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody TypeAbsence formData) {
		TypeAbsence typeAbsence = new TypeAbsence();
		typeAbsenceService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody TypeAbsence formData) {
		TypeAbsence typeAbsence = new TypeAbsence();
		typeAbsenceService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{libelle}")
	public void delete(@PathVariable String libelle) {
		typeAbsenceService.delete(libelle);
	}
}
