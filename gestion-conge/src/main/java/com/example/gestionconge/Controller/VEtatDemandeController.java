package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.VEtatDemande;
import com.example.gestionconge.Model.Service.VEtatDemandeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Date;
import java.sql.Date;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Controller
@RequestMapping("v_etat_demande")
public class VEtatDemandeController{

	private final VEtatDemandeService vEtatDemandeService;

	public VEtatDemandeController(VEtatDemandeService vEtatDemandeService){this.vEtatDemandeService = vEtatDemandeService;}



	/* -- READ ONE -- */
	@GetMapping("/{idDemandeConge}")
	public Optional<VEtatDemande> getOne(@PathVariable String idDemandeConge) { 
		 Optional<VEtatDemande> vEtatDemande = this.vEtatDemandeService.getOne(idDemandeConge);
		return vEtatDemande;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<VEtatDemande> getAll() { 
		List<VEtatDemande> listVEtatDemande = this.vEtatDemandeService.getAll();
		return listVEtatDemande;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody VEtatDemande formData) {
		VEtatDemande vEtatDemande = new VEtatDemande();
		vEtatDemandeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody VEtatDemande formData) {
		VEtatDemande vEtatDemande = new VEtatDemande();
		vEtatDemandeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{idDemandeConge}")
	public void delete(@PathVariable String idDemandeConge) {
		vEtatDemandeService.delete(idDemandeConge);
	}
}
