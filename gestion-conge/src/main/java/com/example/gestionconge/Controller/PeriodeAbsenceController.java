package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.PeriodeAbsence;
import com.example.gestionconge.Model.Service.PeriodeAbsenceService;
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
@RequestMapping("periode_absence")
public class PeriodeAbsenceController{

	private final PeriodeAbsenceService periodeAbsenceService;

	public PeriodeAbsenceController(PeriodeAbsenceService periodeAbsenceService){this.periodeAbsenceService = periodeAbsenceService;}



	/* -- READ ONE -- */
	@GetMapping("/{id}")
	public Optional<PeriodeAbsence> getOne(@PathVariable String id) { 
		 Optional<PeriodeAbsence> periodeAbsence = this.periodeAbsenceService.getOne(id);
		return periodeAbsence;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<PeriodeAbsence> getAll() { 
		List<PeriodeAbsence> listPeriodeAbsence = this.periodeAbsenceService.getAll();
		return listPeriodeAbsence;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody PeriodeAbsence formData) {
		PeriodeAbsence periodeAbsence = new PeriodeAbsence();
		periodeAbsenceService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody PeriodeAbsence formData) {
		PeriodeAbsence periodeAbsence = new PeriodeAbsence();
		periodeAbsenceService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable String id) {
		periodeAbsenceService.delete(id);
	}
}
