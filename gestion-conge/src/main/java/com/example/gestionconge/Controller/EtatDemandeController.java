package com.example.gestionconge.Controller;

import com.example.gestionconge.Model.Entity.EtatDemande;
import com.example.gestionconge.Model.Service.EtatDemandeService;
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
@RequestMapping("etat_demande")
public class EtatDemandeController{

	private final EtatDemandeService etatDemandeService;

	public EtatDemandeController(EtatDemandeService etatDemandeService){this.etatDemandeService = etatDemandeService;}



	/* -- READ ONE -- */
	@GetMapping("/{libelle}")
	public Optional<EtatDemande> getOne(@PathVariable String libelle) { 
		 Optional<EtatDemande> etatDemande = this.etatDemandeService.getOne(libelle);
		return etatDemande;
	}

	/* -- READ -- */
	@GetMapping("lists")
	public List<EtatDemande> getAll() { 
		List<EtatDemande> listEtatDemande = this.etatDemandeService.getAll();
		return listEtatDemande;
	}

	/* -- CREATE -- */
	@PostMapping("create")
	public void create(@RequestBody EtatDemande formData) {
		EtatDemande etatDemande = new EtatDemande();
		etatDemandeService.create(formData);
	}


	/* -- UPDATE -- */
	@PutMapping("update")
	public void update(@RequestBody EtatDemande formData) {
		EtatDemande etatDemande = new EtatDemande();
		etatDemandeService.create(formData);
	}


	/* -- DELETE -- */
	@DeleteMapping("delete/{libelle}")
	public void delete(@PathVariable String libelle) {
		etatDemandeService.delete(libelle);
	}
}
