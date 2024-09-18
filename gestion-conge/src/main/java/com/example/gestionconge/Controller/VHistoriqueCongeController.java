package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.VHistoriqueConge;
import com.example.gestionconge.Model.Service.VHistoriqueCongeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("v_historique_conge")
public class VHistoriqueCongeController{

	private final VHistoriqueCongeService vHistoriqueCongeService;

	public VHistoriqueCongeController(VHistoriqueCongeService vHistoriqueCongeService){this.vHistoriqueCongeService = vHistoriqueCongeService;}



	/* -- READ ONE -- */
	@GetMapping("/{numLigne}")
	public Optional<VHistoriqueConge> getOne(@PathVariable Long numLigne) { 
		 Optional<VHistoriqueConge> vHistoriqueConge = this.vHistoriqueCongeService.getOne(numLigne);
		return vHistoriqueConge;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VHistoriqueConge> getAll() { 
		List<VHistoriqueConge> listVHistoriqueConge = this.vHistoriqueCongeService.getAll();
		return listVHistoriqueConge;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody VHistoriqueConge formData) {
		VHistoriqueConge vHistoriqueConge = new VHistoriqueConge();
		vHistoriqueCongeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody VHistoriqueConge formData) {
		VHistoriqueConge vHistoriqueConge = new VHistoriqueConge();
		vHistoriqueCongeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{numLigne}")
	public void delete(@PathVariable Long numLigne) {
		vHistoriqueCongeService.delete(numLigne);
	}
}
